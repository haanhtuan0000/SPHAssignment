package com.haanhtuan.sphassignment

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haanhtuan.sphassignment.data.LocalUsageDataSource
import com.haanhtuan.sphassignment.data.MobileUsageDataSource
import com.haanhtuan.sphassignment.data.RemoteMobileUsageDataSource
import com.haanhtuan.sphassignment.data.model.Quarter
import com.haanhtuan.sphassignment.data.model.Year
import com.haanhtuan.sphassignment.di.LocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val remoteRepo: RemoteMobileUsageDataSource,
    private var localRepo: LocalUsageDataSource
) :
    ViewModel() {
    private var items = MutableLiveData<List<Year>>()

    fun getItems() = items

    init {
        loadData()
    }

    private fun loadData() {

        var quarters: List<Quarter> = ArrayList()
//        viewModelScope.launch {
//            remoteRepo.getAllQuarters {
//                quarters = it
//                val temp = Utils().convertDataForDisplay(it)
//                items.postValue(temp)
//            }
//
//            withContext(Dispatchers.IO) {
//                localRepo.clearData()
//                localRepo.insertAll(quarters)
//            }
//        }

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                localRepo.getAllQuarters { quarters = it }
                items.postValue(Utils().convertDataForDisplay(quarters))
            }


        }
    }

}