package com.haanhtuan.sphassignment.data

import com.haanhtuan.sphassignment.data.model.Quarter
import javax.inject.Inject

class LocalUsageDataSource @Inject constructor(private val quarterDao: QuarterDao) :
    MobileUsageDataSource {

    override suspend fun getAllQuarters(callback: (List<Quarter>) -> Unit) {
        val quarters = quarterDao.getAll()
        callback(quarters)
    }

    suspend fun insertAll(quarters: List<Quarter>) {
        quarterDao.insertAll(quarters)
    }
}