package com.haanhtuan.sphassignment.network

import com.haanhtuan.sphassignment.data.model.UsageResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface IAppApi {
    /**
     * Get mobile usage all time
     */
    @GET("datastore_search?resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f&limit=100")
   suspend fun getSummary(): Response<UsageResponse>
}