package com.darabi.testCustomView.repository

sealed class ResponseWrapper <I> {

    data class Data <D> constructor(val data: D): ResponseWrapper<D>()

    data class Error <D> constructor(val error: Throwable): ResponseWrapper<D>()
}