package com.haanhtuan.sphassignment.network

import com.haanhtuan.sphassignment.data.model.UsageResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IAppApi {
    /**
     * Get mobile usage all time
     */
    @GET("datastore_search")
    fun getSummary(
        @Query("resource_id") resourceId: String,
        @Query("limit") limit: Int
    ): Call<UsageResponse>
}