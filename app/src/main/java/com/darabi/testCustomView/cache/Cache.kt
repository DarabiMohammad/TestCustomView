package com.darabi.testCustomView.cache

import com.darabi.testCustomView.model.Profile
import com.darabi.testCustomView.model.Session
import com.darabi.testCustomView.model.UserCredit
import com.darabi.testCustomView.repository.ResponseWrapper

interface Cache {

    fun isRegistered(): Boolean

    suspend fun signUp(session: Session): ResponseWrapper<Unit>

    suspend fun login(credit: UserCredit): ResponseWrapper<Unit>

    suspend fun getProfile(): Profile
}