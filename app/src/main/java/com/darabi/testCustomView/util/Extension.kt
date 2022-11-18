package com.darabi.testCustomView.util

import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.darabi.testCustomView.ui.base.BaseFragment

fun EditText.isBlank(): Boolean = this.text.toString().isBlank()

fun BaseFragment.hideSoftKeyboard() =
    ContextCompat.getSystemService(requireContext(), InputMethodManager::class.java)
        ?.hideSoftInputFromWindow(view?.windowToken, 0)