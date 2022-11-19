package com.darabi.testCustomView.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.darabi.custombutton.databinding.ActivityMainBinding
import com.darabi.testCustomView.model.SessionState
import com.darabi.testCustomView.repository.ResponseWrapper
import com.darabi.testCustomView.ui.base.BaseActivity
import com.darabi.testCustomView.ui.base.BaseFragment
import com.darabi.testCustomView.ui.home.HomeFragment
import com.darabi.testCustomView.ui.login.LoginFragment
import com.darabi.testCustomView.ui.signup.SignUpFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var signUpFragment: SignUpFragment

    @Inject
    lateinit var loginFragment: Provider<LoginFragment>

    @Inject
    lateinit var homeFragment: HomeFragment

    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun observeViewModel() {

        mainViewModel.onFragmentBackPressed.observe(this) {

            if (it == firstAddedFragmentTag)
                finish()
            else
                supportFragmentManager.popBackStack(it, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        collectSessionState()
    }

    private fun collectSessionState() = lifecycleScope.launch {

        mainViewModel.sessionState.collectLatest {

            val destination = when (it) {

                SessionState.NOT_REGISTERED -> signUpFragment
                SessionState.REGISTERED -> loginFragment.get()
                SessionState.SIGNED_UP, SessionState.LOGGED_IN -> homeFragment
                else -> loginFragment.get()
            }

            navigateTo(binding.mainContainer.id, destination, shouldReplace = true)
        }
    }
}