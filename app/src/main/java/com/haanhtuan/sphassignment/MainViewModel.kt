package com.haanhtuan.sphassignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haanhtuan.sphassignment.data.LocalUsageDataSource
import com.haanhtuan.sphassignment.data.RemoteMobileUsageDataSource
import com.haanhtuan.sphassignment.data.model.Quarter
import com.haanhtuan.sphassignment.data.model.UsageResponse
import com.haanhtuan.sphassignment.data.model.Year
import com.haanhtuan.sphassignment.network.DataErrorResponse
import com.haanhtuan.sphassignment.network.DataSuccessResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteRepo: RemoteMobileUsageDataSource,
    private var localRepo: LocalUsageDataSource
) :
    ViewModel() {
    private var years = MutableLiveData<List<Year>>()
    fun getItems() = years

    private var errorMessage = MutableLiveData<String>()
    fun getErrorMessage() = errorMessage

    private var quarters: List<Quarter> = ArrayList()

    init {
        loadData()
    }

    private fun loadData() {
        if (MyApplication.instance.isNetworkConnected) { //network available
            getDataFromRemoteServer()
        } else {  //offline
            getDataFromLocal()
        }
    }

    //cache data
    fun insertToDb() {
        if (MyApplication.instance.isNetworkConnected)
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    localRepo.clearData()
                    localRepo.insertAll(quarters)
                }
            }
    }

    private fun getListQuarter(body: UsageResponse): List<Quarter> {
        return body.result?.records ?: arrayListOf()
    }

    private fun getDataFromRemoteServer() {
        viewModelScope.launch {
            remoteRepo.getAllQuarters { dataResponse ->
                if (dataResponse is DataSuccessResponse) {
                    quarters = getListQuarter(dataResponse.body)
                    years.postValue(Utils().convertDataForDisplay(quarters))
                } else {
                    errorMessage.postValue((dataResponse as DataErrorResponse).errorMessage)
                }
            }
        }
    }

    private fun getDataFromLocal() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                localRepo.getAllQuarters { dataResponse ->
                    if (dataResponse is DataSuccessResponse) {
                        quarters = getListQuarter(dataResponse.body)
                    }
                }
                years.postValue(Utils().convertDataForDisplay(quarters))
            }
        }
    }

}