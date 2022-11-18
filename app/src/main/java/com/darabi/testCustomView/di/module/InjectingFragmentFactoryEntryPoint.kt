package com.darabi.testCustomView.di.module

import com.darabi.testCustomView.util.factory.InjectingFragmentFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface InjectingFragmentFactoryEntryPoint {

    fun getFragmentFactory(): InjectingFragmentFactory
}