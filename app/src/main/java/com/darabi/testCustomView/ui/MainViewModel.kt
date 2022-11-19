package com.darabi.testCustomView.ui

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.darabi.testCustomView.model.SessionState
import com.darabi.testCustomView.ui.base.BaseViewModel
import com.darabi.testCustomView.ui.home.AbstractTimer
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor() : BaseViewModel() {

    val onFragmentBackPressed by lazy { MutableLiveData<String>() }
    val sessionState by lazy { repository.sessionState }

    private val defaultTimeInterval = 1000L
    private var timer: CountDownTimer? = null

    fun initSessionConstraint(constraint: Long) {

        if (sessionState.value == SessionState.TIMEOUT || sessionState.value == SessionState.LOGGED_OUT)
            return

        timer?.cancel()

        timer = object : AbstractTimer(constraint, defaultTimeInterval) {

            override fun onFinish() {
                sessionState.value = SessionState.TIMEOUT
            }

        }.start()
    }
}