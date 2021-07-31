package com.haanhtuan.sphassignment.data

import com.haanhtuan.sphassignment.data.model.Quarter
import com.haanhtuan.sphassignment.data.model.Records
import com.haanhtuan.sphassignment.data.model.UsageResponse
import com.haanhtuan.sphassignment.network.DataResponse
import retrofit2.Response
import javax.inject.Inject

class LocalUsageDataSource @Inject constructor(private val quarterDao: QuarterDao) :
    MobileUsageDataSource {

    suspend fun insertAll(quarters: List<Quarter>) {
        quarterDao.insertAll(quarters)
    }

    suspend fun clearData() {
        quarterDao.nukeTable()
    }

    override suspend fun getAllQuarters(callback: (DataResponse<UsageResponse>) -> Unit) {
        val quarters = quarterDao.getAll()
        callback(createDataResponse(quarters))
    }

    private fun createDataResponse(quarters: List<Quarter>):DataResponse<UsageResponse>{
        val usageResponse = UsageResponse(result = Records(quarters))
        return DataResponse.create(Response.success(usageResponse))
    }
}