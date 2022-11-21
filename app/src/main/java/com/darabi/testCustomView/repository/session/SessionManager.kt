package com.darabi.testCustomView.repository.session

import com.darabi.testCustomView.model.RestrictionMode
import com.darabi.testCustomView.model.SessionState
import com.darabi.testCustomView.repository.ResponseWrapper
import kotlinx.coroutines.flow.MutableStateFlow

interface SessionManager {

    fun getSessionStateLiveData(): MutableStateFlow<SessionState>

    fun setSessionRestriction(restrictionMode: RestrictionMode)

    fun <T> updateStateIfNeeded(state: SessionState, response: ResponseWrapper<T>): ResponseWrapper<T>
}