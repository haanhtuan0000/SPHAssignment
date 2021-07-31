package com.haanhtuan.sphassignment.data

import com.haanhtuan.sphassignment.data.model.UsageResponse
import com.haanhtuan.sphassignment.network.DataResponse
import com.haanhtuan.sphassignment.network.IAppApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteMobileUsageDataSource(private val appApi: IAppApi) : MobileUsageDataSource {
    override suspend fun getAllQuarters(callback: (DataResponse<UsageResponse>) -> Unit) {
        val resourceId = "a807b7ab-6cad-4aa6-87d0-e283a7353a0f"
        val limit = 100

        appApi.getSummary(resourceId, limit).enqueue(object : Callback<UsageResponse> {
            override fun onResponse(call: Call<UsageResponse>, response: Response<UsageResponse>) {
                val dataResponse = DataResponse.create(response)
                callback(dataResponse)
            }

            override fun onFailure(call: Call<UsageResponse>, t: Throwable) {
                t.printStackTrace()
                callback(DataResponse.createError(0, t))
            }
        })
    }
}