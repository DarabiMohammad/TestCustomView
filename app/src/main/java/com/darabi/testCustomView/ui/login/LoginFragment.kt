package com.darabi.testCustomView.ui.login

import android.os.Bundle
import android.view.View
import com.darabi.custombutton.R
import com.darabi.custombutton.databinding.FragmentLoginBinding
import com.darabi.testCustomView.ui.base.BaseFragment
import com.darabi.testCustomView.util.isBlank
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment @Inject constructor() : BaseFragment() {

    override val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {

        binding.btnLogin.setOnClickListener {

            if (binding.edtUsername.isBlank())
                showToast(getString(R.string.blank_username))
            else if (binding.edtPassword.isBlank())
                showToast(getString(R.string.blank_password))
            else {

            }
        }
    }
}