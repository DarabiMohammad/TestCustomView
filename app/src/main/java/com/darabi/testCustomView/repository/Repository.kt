package com.darabi.testCustomView.repository

import com.darabi.testCustomView.model.*
import kotlinx.coroutines.flow.MutableStateFlow

interface Repository {

    fun getSessionStateLiveData(): MutableStateFlow<SessionState>

    fun setSessionRestriction(restrictionMode: RestrictionMode)

    suspend fun signUp(session: Session): ResponseWrapper<Unit>

    suspend fun login(credit: UserCredit): ResponseWrapper<Unit>

    suspend fun logout(): ResponseWrapper<Unit>

    suspend fun getProfile(): ResponseWrapper<Profile>
}