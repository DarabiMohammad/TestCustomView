package com.darabi.testCustomView.repository.session

import android.os.CountDownTimer
import com.darabi.testCustomView.cache.Cache
import com.darabi.testCustomView.model.RestrictionMode
import com.darabi.testCustomView.model.SessionState
import com.darabi.testCustomView.repository.AbstractTimer
import com.darabi.testCustomView.repository.ResponseWrapper
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class SessionManagerImpl @Inject constructor(
    private val cache: Cache
) : SessionManager {

    private val defaultTimeInterval = 1000L
    private var timer: CountDownTimer? = null

    private val sessionState by lazy { MutableStateFlow(getSessionState()) }

    override fun getSessionStateLiveData(): MutableStateFlow<SessionState> = sessionState

    override fun setSessionRestriction(restrictionMode: RestrictionMode) =
        setRestriction(restrictionMode.timeout)

    private fun getSessionState(): SessionState = cache.isRegistered().run {
        if (this) SessionState.REGISTERED else SessionState.NOT_REGISTERED
    }

    /**
     * checks whether [response] was successful or not, and if it contains [ResponseWrapper.Data] then
     * updates [sessionState] value with given [state].
     */
    override fun <T> updateStateIfNeeded(state: SessionState, response: ResponseWrapper<T>): ResponseWrapper<T> {
        if (response is ResponseWrapper.Data)
            sessionState.value = state
        return response
    }

    private fun setRestriction(sessionTimeout: Long) {

        if (sessionState.value == SessionState.TIMEOUT || sessionState.value == SessionState.LOGGED_OUT)
            return

        timer?.cancel()

        timer = object : AbstractTimer(sessionTimeout, defaultTimeInterval) {

            override fun onFinish() {
                sessionState.value = SessionState.TIMEOUT
            }

        }.start()
    }
}