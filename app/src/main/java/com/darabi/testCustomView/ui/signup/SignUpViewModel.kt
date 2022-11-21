package com.darabi.testCustomView.ui.signup

import androidx.lifecycle.liveData
import com.darabi.testCustomView.model.Session
import com.darabi.testCustomView.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor() : BaseViewModel() {

    fun signUp(session: Session) = liveData {
        emit(repository.signUp(session))
    }
}