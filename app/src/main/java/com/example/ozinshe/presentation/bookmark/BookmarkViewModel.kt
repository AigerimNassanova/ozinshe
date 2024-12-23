package com.example.ozinshe.presentation.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import com.example.ozinshe.domain.model.FavoriteMoviesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookmarkViewModel(): ViewModel() {
    private var _favouriteMoviesResponse: MutableLiveData<FavoriteMoviesResponse> = MutableLiveData()
    val favouriteMoviesResponse: LiveData<FavoriteMoviesResponse> = _favouriteMoviesResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    fun getFavourite(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            kotlin.runCatching { response.getFavourite("Bearer $token") }
                .onSuccess {
                    _favouriteMoviesResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}