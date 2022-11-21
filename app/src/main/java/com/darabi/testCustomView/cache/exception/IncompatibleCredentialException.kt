package com.darabi.testCustomView.cache.exception

/**
 * represents incompatibility of given credentials compared to persisted one on local storage.
 */
class IncompatibleCredentialException : Throwable {

    constructor(message: String?) : super(message)

    constructor(cause: Throwable?) : super(cause)

    constructor(message: String?, cause: Throwable?) : super(message, cause)
}