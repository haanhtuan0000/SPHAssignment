package com.haanhtuan.sphassignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haanhtuan.sphassignment.data.RemoteMobileUsageDataSource
import com.haanhtuan.sphassignment.data.model.Quarter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val remoteRepo: RemoteMobileUsageDataSource) :
    ViewModel() {
    private val countryLiveData = MutableLiveData<List<Quarter>?>()

    fun getCountry() = countryLiveData

    init {
        loadCountries()
    }

    private fun loadCountries() {
        viewModelScope.launch {
            val countries = remoteRepo.getAllQuarters { }
//            when (countries.isSuccessful) {
//                true -> {
//                    with(countries.body().orEmpty()) {
//                        var countryList = listOf<Country>()
//                        forEach { (_, _, _, _, _, _, capital, _, _, _, _, _, _, _, name) ->
//                            countryList = countryList + Country(name, capital)
//                        }
//                        countryLiveData.postValue(countryList)
//                    }
//                }
//                else -> {
//                    Timber.e(countries.message())
//                }
//            }
        }
    }


}