package com.darabi.testCustomView.ui

import androidx.lifecycle.MutableLiveData
import com.darabi.testCustomView.model.RestrictionMode
import com.darabi.testCustomView.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class MainViewModel @Inject constructor() : BaseViewModel() {

    val onFragmentBackPressed by lazy { MutableLiveData<String>() }

    private val sessionState by lazy { repository.getSessionStateLiveData() }

    fun getSessionStateLiveData() = sessionState

    fun setForegroundRestriction() = repository.setSessionRestriction(RestrictionMode.Foreground)

    fun setBackgroundRestriction() = repository.setSessionRestriction(RestrictionMode.Background)
}