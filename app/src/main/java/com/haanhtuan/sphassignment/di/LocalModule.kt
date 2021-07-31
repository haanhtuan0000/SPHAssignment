package com.haanhtuan.sphassignment.di

import android.content.Context
import androidx.room.Room
import com.haanhtuan.sphassignment.data.AppDatabase
import com.haanhtuan.sphassignment.data.LocalUsageDataSource
import com.haanhtuan.sphassignment.data.QuarterDao
import com.haanhtuan.sphassignment.data.RemoteMobileUsageDataSource
import com.haanhtuan.sphassignment.network.IAppApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Singleton

@InstallIn(ViewModelComponent::class)
@Module
class LocalModule {
    @Provides
    @ViewModelScoped
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "mobile_usage.db"
        ).build()
    }

    @Provides
    fun provideLogDao(database: AppDatabase): QuarterDao {
        return database.quarterDao()
    }
    @ViewModelScoped
    @Provides
    fun providesRepository(quarterDao: QuarterDao) = LocalUsageDataSource(quarterDao)
}