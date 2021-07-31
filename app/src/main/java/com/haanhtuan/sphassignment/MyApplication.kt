package com.haanhtuan.sphassignment

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import com.haanhtuan.sphassignment.data.helper.NetworkHelper
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication : Application() {

    var isNetworkConnected: Boolean = false

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        isNetworkConnected = NetworkHelper.hasNetworkConnected(applicationContext)
    }

    init {
        instance = this
    }

    companion object {
        @get:Synchronized
        lateinit var instance: MyApplication
    }
}