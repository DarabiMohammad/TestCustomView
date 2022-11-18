package com.darabi.testCustomView.cache

import com.darabi.testCustomView.repository.ResponseWrapper
import com.darabi.testCustomView.model.Profile
import com.darabi.testCustomView.model.Session
import com.darabi.testCustomView.model.UserCredit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CacheImpl @Inject constructor(
    private val prefsManager: PrefsManager
) : Cache {

    override suspend fun isSignedUp(): ResponseWrapper<Boolean> = safeCall {
        prefsManager.isSignedUp()
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