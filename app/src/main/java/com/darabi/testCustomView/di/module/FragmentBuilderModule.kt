package com.darabi.testCustomView.di.module

import androidx.fragment.app.Fragment
import com.darabi.testCustomView.di.annotation.FragmentKey
import com.darabi.testCustomView.ui.home.HomeFragment
import com.darabi.testCustomView.ui.login.LoginFragment
import com.darabi.testCustomView.ui.signup.SignUpFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
abstract class FragmentBuilderModule {

    @Binds
    @IntoMap
    @FragmentKey(LoginFragment::class)
    abstract fun bindLoginFragment(fragment: LoginFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(SignUpFragment::class)
    abstract fun bindSignUpFragment(fragment: SignUpFragment): Fragment

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    abstract fun bindHomeFragment(fragment: HomeFragment): Fragment
}