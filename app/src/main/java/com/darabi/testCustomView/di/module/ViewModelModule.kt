package com.darabi.testCustomView.di.module

import com.darabi.testCustomView.repository.Repository
import com.darabi.testCustomView.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindRepository(repository: RepositoryImpl): Repository
}