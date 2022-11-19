package com.darabi.testCustomView.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.darabi.testCustomView.model.Profile
import com.darabi.testCustomView.model.Session
import com.darabi.testCustomView.model.SessionState
import com.darabi.testCustomView.model.UserCredit
import kotlinx.coroutines.flow.MutableStateFlow

interface Repository {

    val sessionState: MutableStateFlow<SessionState>

    suspend fun signUp(session: Session): ResponseWrapper<Boolean>

    suspend fun login(credit: UserCredit): ResponseWrapper<Boolean>

    suspend fun getProfile(): ResponseWrapper<Profile>
}