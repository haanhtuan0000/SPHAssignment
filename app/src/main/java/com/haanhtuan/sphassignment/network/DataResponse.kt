package com.haanhtuan.sphassignment.network

import com.google.gson.Gson
import com.haanhtuan.sphassignment.data.helper.Constant
import com.haanhtuan.sphassignment.data.model.ErrorModelResponse
import retrofit2.Response
import java.net.ConnectException

internal const val UNKNOWN_CODE = -1

sealed class DataResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): DataResponse<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    DataEmptyResponse()
                } else {
                    DataSuccessResponse(body)
                }
            } else {
                return DataErrorResponse(response.code(), handleHttpError(response))
            }
        }

        fun <T> createError(errorCode: Int, error: Throwable): DataResponse<T> {
            return when (error) {
                is ConnectException -> {
                    DataErrorResponse(errorCode, "Connection error")
                }
                else -> {
                    DataErrorResponse(
                        errorCode,
                        error.message ?: "An Unexpected Error Occurred"
                    )
                }
            }
        }

        fun <T> handleHttpError(response: Response<T>): String {
            return when (response.code()) {
                Constant.INTERNAL_SERVER_ERROR -> {
                    "Internal server error"
                }
                else -> {
                    getErrorMessage(response)
                }
            }
        }

        private fun <T> getErrorMessage(response: Response<T>): String {
            var errorMsg = ""
            response.errorBody()?.string()?.let {
                try {
                    val errorObj = Gson().fromJson(it, ErrorModelResponse::class.java)
                    errorMsg = errorObj?.message ?: ""
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (errorMsg.isEmpty()) {
                errorMsg = "An unexpected error occurred"
            }
            return errorMsg
        }
    }
}

class DataEmptyResponse<T> : DataResponse<T>()
data class DataErrorResponse<T>(val errorCode: Int, val errorMessage: String) : DataResponse<T>()
data class DataSuccessResponse<T>(val body: T) : DataResponse<T>()