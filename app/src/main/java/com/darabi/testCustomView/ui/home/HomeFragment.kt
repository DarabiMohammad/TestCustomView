package com.darabi.testCustomView.ui.home

import com.darabi.custombutton.databinding.FragmentHomeBinding
import com.darabi.testCustomView.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment @Inject constructor() : BaseFragment() {

    override val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

}