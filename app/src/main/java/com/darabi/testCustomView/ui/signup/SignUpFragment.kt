package com.darabi.testCustomView.ui.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.darabi.custombutton.R
import com.darabi.custombutton.databinding.FragmentSignUpBinding
import com.darabi.testCustomView.model.Session
import com.darabi.testCustomView.repository.ResponseWrapper
import com.darabi.testCustomView.ui.base.BaseFragment
import com.darabi.testCustomView.util.hideSoftKeyboard
import com.darabi.testCustomView.util.isBlank
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment @Inject constructor() : BaseFragment(), Observer<ResponseWrapper<Boolean>> {

    private val viewModel: SignUpViewModel by viewModels()
    override val binding by lazy { FragmentSignUpBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {

        binding.btnSignUp.setOnClickListener {

            hideSoftKeyboard()

            if (binding.edtFullName.isBlank())
                showToast(getString(R.string.blank_full_name))
            if (binding.edtUsername.isBlank())
                showToast(getString(R.string.blank_username))
            else if (binding.edtPassword.isBlank())
                showToast(getString(R.string.blank_password))
            else {
                val session = Session(
                    binding.edtFullName.text.toString(),
                    binding.edtUsername.text.toString(),
                    binding.edtPassword.text.toString()
                )
                viewModel.signUp(session).observe(viewLifecycleOwner, this@SignUpFragment)
            }
        }
    }

    override fun onChanged(response: ResponseWrapper<Boolean>) {
        when (response) {

            is ResponseWrapper.Data -> mainViewModel.onSignedUp.value = response.data
            is ResponseWrapper.Error -> showToast("${response.error.message}")
        }
    }
}