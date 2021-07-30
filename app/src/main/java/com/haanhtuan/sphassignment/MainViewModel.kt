package com.haanhtuan.sphassignment

import android.os.storage.StorageVolume
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haanhtuan.sphassignment.data.RemoteMobileUsageDataSource
import com.haanhtuan.sphassignment.data.model.Quarter
import com.haanhtuan.sphassignment.data.model.Year
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.internal.indexOfFirstNonAsciiWhitespace
import timber.log.Timber
import java.lang.NumberFormatException
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val remoteRepo: RemoteMobileUsageDataSource) :
    ViewModel() {
    private var items = MutableLiveData<List<Year>>()

    fun getItems() = items

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            remoteRepo.getAllQuarters {
                val temp = Utils().convertDataForDisplay(it)
                items.postValue(temp)
                Timber.e("tuan: "+ temp.size)
            }
        }

    }

}