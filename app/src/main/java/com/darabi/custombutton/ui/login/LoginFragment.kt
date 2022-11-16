package com.darabi.custombutton.ui.login

import com.darabi.custombutton.databinding.FragmentLoginBinding
import com.darabi.custombutton.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment @Inject constructor() : BaseFragment() {

    override val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }

}