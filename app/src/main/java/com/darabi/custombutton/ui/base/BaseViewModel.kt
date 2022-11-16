package com.darabi.custombutton.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData

abstract class BaseViewModel : ViewModel() {

//    protected inline fun <T> loadingLiveData (
//        crossinline function: suspend () -> ResponseWrapper<T>
//    ): LiveData<ResponseWrapper<T>> = liveData {
//        emit(ResponseWrapper.loading())
//        emit(function())
//    }
}