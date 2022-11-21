package com.darabi.testCustomView.cache.exception

/**
 * represents a failure in data insertion to local storage.
 */
class InsertionException : Throwable {

    constructor(message: String?) : super(message)

    constructor(cause: Throwable?) : super(cause)

    constructor(message: String?, cause: Throwable?) : super(message, cause)
}