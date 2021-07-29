package com.haanhtuan.sphassignment.network

import com.haanhtuan.sphassignment.data.model.UsageResponse
import retrofit2.Call
import retrofit2.http.*

interface IAppApi {
    /**
     * Get mobile usage all time
     */
    @GET("/summary")
    fun getSummary(): Call<UsageResponse>
}