package com.darabi.testCustomView.di.module

import com.darabi.testCustomView.cache.Cache
import com.darabi.testCustomView.cache.CacheImpl
import com.darabi.testCustomView.repository.session.SessionManager
import com.darabi.testCustomView.repository.session.SessionManagerImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindCache(cache: CacheImpl): Cache

    @Binds
    abstract fun bindSessionManager(sessionManager: SessionManagerImpl): SessionManager
}