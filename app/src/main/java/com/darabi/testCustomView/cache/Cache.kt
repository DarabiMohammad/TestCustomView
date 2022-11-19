package com.darabi.testCustomView.cache

import com.darabi.testCustomView.model.SessionState
import com.darabi.testCustomView.repository.Repository

interface Cache : Repository {

    fun getSessionState(): SessionState
}