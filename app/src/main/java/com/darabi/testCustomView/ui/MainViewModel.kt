package com.darabi.testCustomView.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.darabi.testCustomView.repository.ResponseWrapper
import com.darabi.testCustomView.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    val onFragmentBackPressed by lazy { MutableLiveData<String>() }
    val onSignedUp by lazy { MutableLiveData<Boolean>() }

    fun checkSessionStatus() = liveData {
        emit(repository.isSignedUp())
    }
}