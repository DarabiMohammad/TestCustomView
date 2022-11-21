package com.darabi.testCustomView.ui.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.darabi.custombutton.R
import com.darabi.custombutton.databinding.FragmentHomeBinding
import com.darabi.testCustomView.model.Profile
import com.darabi.testCustomView.model.SessionState
import com.darabi.testCustomView.repository.ResponseWrapper
import com.darabi.testCustomView.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() : BaseFragment(), Observer<ResponseWrapper<Profile>> {

    private val viewModel: HomeViewModel by viewModels()

    override val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProfile().observe(viewLifecycleOwner, this)
        initViews()
    }

    override fun onResume() {
        super.onResume()

        mainViewModel.setForegroundRestriction()
    }

    override fun onStop() {
        super.onStop()

        mainViewModel.setBackgroundRestriction()
    }

    override fun onChanged(response: ResponseWrapper<Profile>) {

        when (response) {

            is ResponseWrapper.Data ->
                binding.txtFullName.text = getString(R.string.welcome_user, response.data.fullName)

            is ResponseWrapper.Error -> showToast("${response.error.message}")
        }
    }

    private fun initViews() {

        binding.btnLogout.setOnClickListener {

            viewModel.logout().observe(viewLifecycleOwner) { response ->

                if (response is ResponseWrapper.Error) {
                    showToast("${response.error.message}")
                }
            }
        }
    }
}