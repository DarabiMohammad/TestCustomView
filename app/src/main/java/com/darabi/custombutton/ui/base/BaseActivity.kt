package com.darabi.custombutton.ui.base

import android.os.Bundle
import androidx.activity.addCallback
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.darabi.custombutton.di.module.InjectingFragmentFactoryEntryPoint
import com.darabi.custombutton.ui.MainViewModel
import dagger.hilt.android.EntryPointAccessors

abstract class BaseActivity : AppCompatActivity() {

    protected abstract val binding: ViewBinding

    protected val mainViewModel: MainViewModel by viewModels()

    abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        supportFragmentManager.fragmentFactory = EntryPointAccessors.fromActivity(
            this, InjectingFragmentFactoryEntryPoint::class.java
        ).getFragmentFactory()

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        registerOnBackPressedCallback()
    }

    private fun registerOnBackPressedCallback() {

        onBackPressedDispatcher.addCallback {
            getBaseFragmentOrNull()?.onBackPressed() ?: onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getBaseFragmentOrNull(): BaseFragment? = (supportFragmentManager.fragments.findLast {
        it is BaseFragment
    } as BaseFragment?)
}