package com.darabi.testCustomView.ui.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.darabi.custombutton.R
import com.darabi.custombutton.databinding.FragmentLoginBinding
import com.darabi.testCustomView.model.SessionState
import com.darabi.testCustomView.model.UserCredit
import com.darabi.testCustomView.repository.ResponseWrapper
import com.darabi.testCustomView.ui.base.BaseFragment
import com.darabi.testCustomView.util.hideSoftKeyboard
import com.darabi.testCustomView.util.isBlank
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment @Inject constructor() : BaseFragment(), Observer<ResponseWrapper<Boolean>> {

    private val viewModel: LoginViewModel by viewModels()
    override val binding by lazy { FragmentLoginBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    override fun onChanged(response: ResponseWrapper<Boolean>) {

        when (response) {

            is ResponseWrapper.Data -> {
                if (response.data)
                    mainViewModel.sessionState.value = SessionState.LOGGED_IN
                else
                    showToast(getString(R.string.wrong_credentials))
            }
            is ResponseWrapper.Error -> showToast("${response.error.message}")
        }
    }

    private fun initViews() {

        binding.btnLogin.setOnClickListener {

            hideSoftKeyboard()

            if (binding.edtUsername.isBlank())
                showToast(getString(R.string.blank_username))
            else if (binding.edtPassword.isBlank())
                showToast(getString(R.string.blank_password))
            else {

                val credit = UserCredit(
                    binding.edtUsername.text.toString(),
                    binding.edtPassword.text.toString(),
                )

                viewModel.login(credit).observe(viewLifecycleOwner, this)
            }
        }
    }
}