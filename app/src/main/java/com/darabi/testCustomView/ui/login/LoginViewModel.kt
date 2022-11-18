package com.darabi.testCustomView.ui.login

import androidx.lifecycle.liveData
import com.darabi.testCustomView.model.UserCredit
import com.darabi.testCustomView.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : BaseViewModel() {

    fun login(credit: UserCredit) = liveData {
        emit(repository.login(credit))
    }
}