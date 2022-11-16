package com.darabi.custombutton.di.module

import com.darabi.custombutton.util.factory.InjectingFragmentFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@EntryPoint
@InstallIn(ActivityComponent::class)
interface InjectingFragmentFactoryEntryPoint {

    fun getFragmentFactory(): InjectingFragmentFactory
}