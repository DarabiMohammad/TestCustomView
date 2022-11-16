package com.darabi.custombutton.di.module

import androidx.fragment.app.Fragment
import com.darabi.custombutton.di.annotation.FragmentKey
import com.darabi.custombutton.ui.login.LoginFragment
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
}