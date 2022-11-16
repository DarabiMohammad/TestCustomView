package com.darabi.custombutton.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.darabi.custombutton.ui.MainViewModel

abstract class BaseFragment : Fragment() {

    val fragmentTag: String get() = this.javaClass.simpleName

    protected abstract val binding: ViewBinding

    protected val mainViewModel: MainViewModel by viewModels( { requireActivity() } )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        binding.root

    open fun onBackPressed(): Unit = lastChildFragmentOrNull().let {
        if (it != null)
            it.onBackPressed()
        else
            mainViewModel.onFragmentBackPressed.value = fragmentTag
    }

    private fun lastChildFragmentOrNull(): BaseFragment? = (childFragmentManager.fragments.findLast {
        it is BaseFragment
    } as BaseFragment?)
}