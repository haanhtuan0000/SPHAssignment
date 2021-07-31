package com.haanhtuan.sphassignment.data

import com.haanhtuan.sphassignment.data.model.UsageResponse
import com.haanhtuan.sphassignment.network.DataResponse

interface MobileUsageDataSource {
    suspend fun getAllQuarters(callback: (DataResponse<UsageResponse>) -> Unit)
}