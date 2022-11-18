package com.darabi.testCustomView.di.module

import com.darabi.testCustomView.cache.Cache
import com.darabi.testCustomView.cache.CacheImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: CacheImpl): Cache
}