package com.darabi.testCustomView.repository

import com.darabi.testCustomView.model.Profile
import com.darabi.testCustomView.model.Session
import com.darabi.testCustomView.model.UserCredit

interface Repository {

    suspend fun isSignedUp(): ResponseWrapper<Boolean>

    suspend fun signUp(session: Session): ResponseWrapper<Boolean>

    suspend fun login(credit: UserCredit): ResponseWrapper<Boolean>

    suspend fun getProfile(): ResponseWrapper<Profile>
}