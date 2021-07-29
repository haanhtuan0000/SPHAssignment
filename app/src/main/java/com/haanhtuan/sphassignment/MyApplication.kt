package com.haanhtuan.sphassignment

import android.app.Application
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
//        initAppComponent(  )
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
//        registerNetworkReceiver()
//        SimpleCacheManager.getInstance(this)
    }
}