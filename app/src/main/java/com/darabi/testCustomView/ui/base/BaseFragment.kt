package com.darabi.testCustomView.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.darabi.testCustomView.ui.MainViewModel

/**
 * Base class for all fragments which are going to be used on [BaseActivity].
 * this class forces it's childes to override the [binding] which stores chile's view.
 *
 * @see BaseActivity
 * @see MainViewModel
 */
abstract class BaseFragment : Fragment() {

    val fragmentTag: String get() = this.javaClass.simpleName

    protected abstract val binding: ViewBinding

    /**
     * Each instance of this fragment can interact with the base activity through this view model.
     */
    protected val mainViewModel: MainViewModel by viewModels( { requireActivity() } )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        binding.root

    /**
     * calls it's most recent added base fragment child's [onBackPressed], which in turn returns it's
     * job as a boolean. true value means that the child is done and ready to be popped off from
     * backstack and false value means the child is in use yet. if no child was found, [fragmentTag]
     * will be emitted to the base activity to say that this fragment isn't in use.
     */
    fun handleBackButton(): Unit = lastChildFragmentOrNull().let {

        if (it?.onBackPressed() == true)
            childFragmentManager.popBackStack(it.fragmentTag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        else
            mainViewModel.onFragmentBackPressed.value = fragmentTag
    }

    protected fun navigateTo(
        containerId: Int,
        fragment: BaseFragment,
        addToBackStack: Boolean = false,
        shouldReplace: Boolean = false
    ) {
        if (fragment.isAdded)
            return
        else childFragmentManager.beginTransaction().also {
            if (shouldReplace) {
                if (addToBackStack)
                    it.addToBackStack(fragment.fragmentTag)
                        .replace(containerId, fragment, fragment.fragmentTag)
                else
                    it.replace(containerId, fragment, fragment.fragmentTag)
            } else {
                if (addToBackStack)
                    it.addToBackStack(fragment.fragmentTag)
                        .add(containerId, fragment, fragment.fragmentTag)
                else
                    it.add(containerId, fragment, fragment.fragmentTag)
            }
        }.commitAllowingStateLoss()
    }

    /**
     * calls it's last base fragment child's [onBackPressed].
     *
     * @return boolean which indicates whether should this fragment be removed from backstack or not.
     */
    protected open fun onBackPressed(): Boolean {
        return lastChildFragmentOrNull()?.onBackPressed() ?: true
    }

    protected fun showToast(message: String) = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()

    private fun lastChildFragmentOrNull(): BaseFragment? = (childFragmentManager.fragments.findLast {
        it is BaseFragment
    } as BaseFragment?)
}