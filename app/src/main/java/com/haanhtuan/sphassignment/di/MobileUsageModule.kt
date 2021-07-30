package com.haanhtuan.sphassignment.di

import com.haanhtuan.sphassignment.data.LocalUsageDataSource
import com.haanhtuan.sphassignment.data.MobileUsageDataSource
import com.haanhtuan.sphassignment.data.RemoteMobileUsageDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class LocalDataSource

@Qualifier
annotation class RemoteDataSource

@InstallIn(ViewModelComponent::class)
@Module
abstract class MobileLocalUsageModule {

    @LocalDataSource
    @Singleton
    @Binds
    abstract fun bindLocalModule(impl: LocalUsageDataSource): MobileUsageDataSource
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class MobileRemoteUsageModule {

    @RemoteDataSource
    @ViewModelScoped
    @Binds
    abstract fun bindRemoteUsage(impl: RemoteMobileUsageDataSource): MobileUsageDataSource
}