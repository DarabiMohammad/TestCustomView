package com.darabi.testCustomView.cache

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darabi.testCustomView.repository.ResponseWrapper
import com.darabi.testCustomView.model.Profile
import com.darabi.testCustomView.model.Session
import com.darabi.testCustomView.model.SessionState
import com.darabi.testCustomView.model.UserCredit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CacheImpl @Inject constructor(
    private val prefsManager: PrefsManager
) : Cache {

    override val sessionState by lazy { MutableStateFlow<SessionState>(getSessionState()) }

//    init {
//        sessionState.value = getSessionState()
//    }

    override fun getSessionState(): SessionState = prefsManager.isSignedUp().run {
        if (this) SessionState.REGISTERED else SessionState.NOT_REGISTERED
    }

    override suspend fun signUp(session: Session): ResponseWrapper<Boolean> = safeCall {
        prefsManager.setFullName(session.fullName) && prefsManager.setUsername(session.username) &&
                prefsManager.setPassword(session.password)
    }

    override suspend fun login(credit: UserCredit): ResponseWrapper<Boolean> = safeCall {
        prefsManager.getUsername() == credit.username && prefsManager.getPassword() == credit.password
    }

    override suspend fun getProfile(): ResponseWrapper<Profile> = safeCall {
        Profile(prefsManager.getFullName()!!)
    }

    /**
     * this function wraps all calls to local storage e.g. shared prefs of database.
     * checks possible throwable errors and returns the result as [ResponseWrapper] instance.
     *
     * @param function is the error prone call to local storage which must be checked.
     *
     * @return [ResponseWrapper] which may include data or thrown error.
     */
    private suspend inline fun <T> safeCall(crossinline function: () -> T): ResponseWrapper<T> = try {

        withContext(Dispatchers.IO) {

            ResponseWrapper.Data(function.invoke())
        }
    } catch (exception: Exception) {

        withContext(Dispatchers.Main) {

            ResponseWrapper.Error(exception)
        }
    }
}