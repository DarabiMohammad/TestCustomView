package com.darabi.testCustomView.ui.home

import android.os.CountDownTimer

abstract class AbstractTimer constructor(
    private val duration: Long, private val interval: Long
) : CountDownTimer(duration, interval) {

    override fun onTick(millisUntilFinished: Long) {
        return
    }

    override fun onFinish() {
        return
    }
}