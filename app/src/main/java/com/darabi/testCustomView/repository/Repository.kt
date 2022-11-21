package com.darabi.testCustomView.repository

import com.darabi.testCustomView.model.Profile
import com.darabi.testCustomView.model.Session
import com.darabi.testCustomView.model.SessionState
import com.darabi.testCustomView.model.UserCredit
import kotlinx.coroutines.flow.MutableStateFlow

interface Repository {

    fun getSessionStateLiveData(): MutableStateFlow<SessionState>

    suspend fun signUp(session: Session): ResponseWrapper<Unit>

    suspend fun login(credit: UserCredit): ResponseWrapper<Unit>

    suspend fun logout(): ResponseWrapper<Unit>

    suspend fun getProfile(): ResponseWrapper<Profile>
}