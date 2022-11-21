package com.darabi.testCustomView.repository

import android.os.CountDownTimer
import com.darabi.testCustomView.cache.Cache
import com.darabi.testCustomView.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val cache: Cache
) : Repository {

    private val defaultTimeInterval = 1000L
    private var timer: CountDownTimer? = null

    private val sessionState by lazy { MutableStateFlow(getSessionState()) }

    override fun getSessionStateLiveData(): MutableStateFlow<SessionState> = sessionState

    override fun setSessionRestriction(restrictionMode: RestrictionMode) =
        setRestriction(restrictionMode.timeout)

    override suspend fun signUp(session: Session): ResponseWrapper<Unit> =
        updateStateIfNeeded(SessionState.SIGNED_UP, cache.signUp(session))

    override suspend fun login(credit: UserCredit): ResponseWrapper<Unit> =
        updateStateIfNeeded(SessionState.LOGGED_IN, cache.login(credit))

    override suspend fun logout(): ResponseWrapper<Unit> =
        updateStateIfNeeded(SessionState.LOGGED_OUT, ResponseWrapper.Data(Unit))

    override suspend fun getProfile(): ResponseWrapper<Profile> = safeCall {
        cache.getProfile()
    }

    private fun getSessionState(): SessionState = cache.isRegistered().run {
        if (this) SessionState.REGISTERED else SessionState.NOT_REGISTERED
    }

    private fun setRestriction(sessionTimeout: Long) {

        if (sessionState.value == SessionState.TIMEOUT || sessionState.value == SessionState.LOGGED_OUT)
            return

        timer?.cancel()

        timer = object : AbstractTimer(sessionTimeout, defaultTimeInterval) {

            override fun onFinish() {
                sessionState.value = SessionState.TIMEOUT
            }

        }.start()
    }

    /**
     * this function wraps all calls to remote or local storage e.g. shared prefs of database.
     * checks possible throwable errors and returns the result as [ResponseWrapper] instance.
     *
     * @param function is the error prone call to local storage which must be checked.
     *
     * @return [ResponseWrapper] which may include data or thrown error.
     */
    private suspend inline fun <T> safeCall(crossinline function: suspend () -> T): ResponseWrapper<T> = try {

        withContext(Dispatchers.IO) {

            ResponseWrapper.Data(function.invoke())
        }
    } catch (exception: Exception) {

        withContext(Dispatchers.Main) {

            ResponseWrapper.Error(exception)
        }
    }

    /**
     * checks whether [response] was successful or not, and if it contains [ResponseWrapper.Data] then
     * updates [sessionState] value with given [state].
     */
    private fun <T> updateStateIfNeeded(state: SessionState, response: ResponseWrapper<T>): ResponseWrapper<T> {
        if (response is ResponseWrapper.Data)
            sessionState.value = state
        return response
    }
}