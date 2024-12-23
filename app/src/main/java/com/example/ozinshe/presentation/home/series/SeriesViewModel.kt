package com.example.ozinshe.presentation.home.series

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import com.example.ozinshe.domain.model.SeriesListResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SeriesViewModel: ViewModel() {
    private var _seriesResponse: MutableLiveData<List<SeriesListResponseItem>> = MutableLiveData()
    val seriesResponse: LiveData<List<SeriesListResponseItem>> = _seriesResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    fun getSeries(token: String, movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            kotlin.runCatching { response.getSeries("Bearer $token", movieId) }
                .onSuccess {
                    Log.d("III", "$it")
                    _seriesResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}