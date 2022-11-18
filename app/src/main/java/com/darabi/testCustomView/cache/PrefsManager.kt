package com.darabi.testCustomView.cache

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PrefsManager @Inject constructor(
    private val application: Application,
) {

    private val prefs: SharedPreferences =
        application.applicationContext.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val fullNameKey = "full_name_key"
    private val usernameKey = "user_name_key"
    private val passwordKey = "password_key"
    private val signUpStatusKey = "sign_up_status_key"

    fun getFullName(): String? = prefs.getString(fullNameKey, null)

    fun setFullName(fullName: String) = putStringValue(fullNameKey, fullName)

    fun getUsername(): String? = prefs.getString(usernameKey, null)

    fun setUsername(userName: String) = putStringValue(usernameKey, userName)

    fun getPassword(): String? = prefs.getString(passwordKey, null)

    fun setPassword(password: String) = putStringValue(passwordKey, password)

    fun isSignedUp(): Boolean = prefs.getString(fullNameKey, null) != null

    private fun putStringValue(key: String, value: String?) =
        prefs.edit().putString(key, value).commit()
}