package com.haanhtuan.sphassignment.data

import com.haanhtuan.sphassignment.data.model.Quarter

interface MobileUsageDataSource {
    suspend fun getAllQuarters(callback: (List<Quarter>) -> Unit)
}