package com.haanhtuan.sphassignment.data

import com.haanhtuan.sphassignment.data.model.Quarter
import com.haanhtuan.sphassignment.network.IAppApi
import timber.log.Timber
import javax.inject.Inject

class RemoteMobileUsageDataSource(private val appApi: IAppApi) : MobileUsageDataSource {
    override suspend fun getAllQuarters(callback: (List<Quarter>) -> Unit) {
        val list = appApi.getSummary()
        list.body()?.result?.records?.let {
            callback(it)
        }
    }
}