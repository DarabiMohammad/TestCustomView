package com.darabi.custombutton.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.darabi.custombutton.R
import com.darabi.custombutton.databinding.ActivityMainBinding
import com.darabi.custombutton.ui.base.BaseActivity
import com.darabi.custombutton.ui.login.LoginFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    lateinit var loginFragment: LoginFragment

    override val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun observeViewModel() {

    }
}