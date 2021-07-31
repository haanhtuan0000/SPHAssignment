package com.haanhtuan.sphassignment

import com.haanhtuan.sphassignment.data.model.Quarter
import com.haanhtuan.sphassignment.data.model.Records
import com.haanhtuan.sphassignment.data.model.UsageResponse
import com.haanhtuan.sphassignment.network.DataResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody
import org.junit.Assert
import org.junit.Test
import retrofit2.Response
import retrofit2.Response.error

class DataResponseTest {
    private fun createQuarterList(): List<Quarter> {
        val quarters = arrayListOf<Quarter>()
        quarters.add(Quarter(volume_of_mobile_data = "10.2324", quarter = "2017-Q2"))
        quarters.add(Quarter(volume_of_mobile_data = "11.2324", quarter = "2017-Q3"))
        return quarters
    }

    private fun createUsageResponse(): UsageResponse {
        return UsageResponse(result = Records(createQuarterList()))
    }

    @Test
    fun handleHttpErrorTest() {
        var response = Response.success(createUsageResponse())
        var result = DataResponse.handleHttpError(response)
        Assert.assertEquals("An unexpected error occurred", result)

        response = error(500, ResponseBody.create("text/plain".toMediaTypeOrNull(), "ERROR_MESSAGE"))
        result = DataResponse.handleHttpError(response)
        Assert.assertEquals("Internal server error", result)
    }
}