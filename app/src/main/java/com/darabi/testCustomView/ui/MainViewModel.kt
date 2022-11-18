package com.darabi.testCustomView.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.darabi.testCustomView.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    val onFragmentBackPressed by lazy { MutableLiveData<String>() }
    val onSigndUp by lazy { MutableLiveData<Boolean>() }
    val onLogin by lazy { MutableLiveData<Boolean>() }

    fun checkSessionStatus() = liveData {
        emit(repository.isSignedUp())
    }
}