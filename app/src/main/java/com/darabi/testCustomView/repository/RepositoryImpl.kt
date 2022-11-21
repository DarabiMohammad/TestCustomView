package com.darabi.testCustomView.repository

import android.os.CountDownTimer
import com.darabi.testCustomView.cache.Cache
import com.darabi.testCustomView.model.*
import com.darabi.testCustomView.repository.session.SessionManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val cache: Cache,
    private val sessionManager: SessionManager,
) : Repository {

    override fun getSessionStateLiveData(): MutableStateFlow<SessionState> =
        sessionManager.getSessionStateLiveData()

    override fun setSessionRestriction(restrictionMode: RestrictionMode) =
        sessionManager.setSessionRestriction(restrictionMode)

    override suspend fun signUp(session: Session): ResponseWrapper<Unit> =
        sessionManager.updateStateIfNeeded(SessionState.SIGNED_UP, cache.signUp(session))

    override suspend fun login(credit: UserCredit): ResponseWrapper<Unit> =
        sessionManager.updateStateIfNeeded(SessionState.LOGGED_IN, cache.login(credit))

    override suspend fun logout(): ResponseWrapper<Unit> =
        sessionManager.updateStateIfNeeded(SessionState.LOGGED_OUT, ResponseWrapper.Data(Unit))

    override suspend fun getProfile(): ResponseWrapper<Profile> = safeCall {
        cache.getProfile()
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
}