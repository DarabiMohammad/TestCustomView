package com.darabi.testCustomView.cache

import com.darabi.testCustomView.cache.exception.IncompatibleCredentialException
import com.darabi.testCustomView.cache.exception.InsertionException
import com.darabi.testCustomView.model.Profile
import com.darabi.testCustomView.model.Session
import com.darabi.testCustomView.model.SessionState
import com.darabi.testCustomView.model.UserCredit
import com.darabi.testCustomView.repository.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CacheImpl @Inject constructor(
    private val prefsManager: PrefsManager
) : Cache {

    override fun isRegistered(): Boolean = prefsManager.isSignedUp()

    override suspend fun signUp(session: Session): ResponseWrapper<Unit> {

        return if (!prefsManager.setFullName(session.fullName))
            ResponseWrapper.Error(InsertionException("Failed to save full name"))
        else if (!prefsManager.setUsername(session.username))
            ResponseWrapper.Error(InsertionException("Failed to save username"))
        else if (!prefsManager.setPassword(session.password))
            ResponseWrapper.Error(InsertionException("Failed to save password"))
        else
            ResponseWrapper.Data(Unit)
    }

    override suspend fun login(credit: UserCredit): ResponseWrapper<Unit> {
        return if (prefsManager.getUsername() != credit.username || prefsManager.getPassword() != credit.password)
            ResponseWrapper.Error(IncompatibleCredentialException("Wrong username or password"))
        else
            ResponseWrapper.Data(Unit)
    }

    override suspend fun getProfile(): Profile = Profile(prefsManager.getFullName()!!)
}