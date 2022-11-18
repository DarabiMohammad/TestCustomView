package com.darabi.testCustomView.repository

import com.darabi.testCustomView.cache.Cache
import com.darabi.testCustomView.model.Profile
import com.darabi.testCustomView.model.Session
import com.darabi.testCustomView.model.UserCredit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val cache: Cache
) : Repository {

    override suspend fun isSignedUp(): ResponseWrapper<Boolean> = cache.isSignedUp()

    override suspend fun signUp(session: Session): ResponseWrapper<Boolean> = cache.signUp(session)

    override suspend fun login(credit: UserCredit): ResponseWrapper<Boolean> = cache.login(credit)

    override suspend fun getProfile(): ResponseWrapper<Profile> = cache.getProfile()
}