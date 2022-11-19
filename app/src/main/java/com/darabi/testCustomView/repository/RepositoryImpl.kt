package com.darabi.testCustomView.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darabi.testCustomView.cache.Cache
import com.darabi.testCustomView.model.Profile
import com.darabi.testCustomView.model.Session
import com.darabi.testCustomView.model.SessionState
import com.darabi.testCustomView.model.UserCredit
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val cache: Cache
) : Repository {

    override val sessionState: MutableStateFlow<SessionState>
        get() = cache.sessionState

    override suspend fun signUp(session: Session): ResponseWrapper<Boolean> = cache.signUp(session)

    override suspend fun login(credit: UserCredit): ResponseWrapper<Boolean> = cache.login(credit)

    override suspend fun getProfile(): ResponseWrapper<Profile> = cache.getProfile()
}