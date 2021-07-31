package com.haanhtuan.sphassignment.data.helper

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import okhttp3.Response

class NetworkHelper(private val context: Context) {
    companion object {
        fun hasNetworkConnected(context: Context): Boolean {
            val conMgr =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = conMgr.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnected
        }
    }
}