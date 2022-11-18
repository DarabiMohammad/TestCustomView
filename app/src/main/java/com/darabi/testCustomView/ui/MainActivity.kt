package com.darabi.testCustomView.ui

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import com.darabi.custombutton.R
import com.darabi.custombutton.databinding.ActivityMainBinding
import com.darabi.testCustomView.repository.ResponseWrapper
import com.darabi.testCustomView.ui.base.BaseActivity
import com.darabi.testCustomView.ui.base.BaseFragment
import com.darabi.testCustomView.ui.home.HomeFragment
import com.darabi.testCustomView.ui.login.LoginFragment
import com.darabi.testCustomView.ui.signup.SignUpFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity(), Observer<ResponseWrapper<Boolean>> {

    @Inject
    lateinit var signUpFragment: SignUpFragment

    @Inject
    lateinit var loginFragment: LoginFragment

    @Inject
    lateinit var homeFragment: HomeFragment

    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.checkSessionStatus().observe(this, this)
    }

    override fun onChanged(response: ResponseWrapper<Boolean>) {

        when (response) {

            is ResponseWrapper.Data -> {

                val destination: BaseFragment = if (response.data)
                    loginFragment
                else
                    signUpFragment
                navigateTo(binding.mainContainer.id, fragment = destination, addToBackStack = true, shouldReplace = true)
            }

            is ResponseWrapper.Error -> showToast("${response.error.message}")
        }
    }

    override fun observeViewModel() {

        mainViewModel.onFragmentBackPressed.observe(this) {

            if (it == firstAddedFragmentTag)
                finish()
            else
                supportFragmentManager.popBackStack(it, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        mainViewModel.onSigndUp.observe(this) {
            navigateTo(binding.mainContainer.id, homeFragment, shouldReplace = true)
        }

        mainViewModel.onLogin.observe(this) {
            navigateTo(binding.mainContainer.id, homeFragment, shouldReplace = true)
        }
    }
}