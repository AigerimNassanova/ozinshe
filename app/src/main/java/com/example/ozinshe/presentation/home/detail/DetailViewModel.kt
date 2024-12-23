package com.example.ozinshe.presentation.home.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import com.example.ozinshe.domain.model.MovieByIdResponse
import com.example.ozinshe.domain.model.MovieIdModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel:ViewModel() {
    private var _moviesByIdResponse: MutableLiveData<MovieByIdResponse> = MutableLiveData()
    val moviesByIdResponse: LiveData<MovieByIdResponse> = _moviesByIdResponse

    private var _moviesAddFavouriteResponse: MutableLiveData<MovieIdModel> = MutableLiveData()
    val moviesAddFavouriteResponse: LiveData<MovieIdModel> = _moviesAddFavouriteResponse

    private var _moviesDeleteFavouriteResponse: MutableLiveData<String> = MutableLiveData()
    val moviesDeleteFavouriteResponse: LiveData<String> = _moviesDeleteFavouriteResponse

    private var _favouriteState: MutableLiveData<Boolean> = MutableLiveData()
    val favouriteState: LiveData<Boolean> = _favouriteState

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    fun getMovieById(token: String, movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            kotlin.runCatching { response.getMovieById("Bearer $token", movieId) }
                .onSuccess {
                    _moviesByIdResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }

    fun addFavourite(token: String, movieId: MovieIdModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            kotlin.runCatching { response.addFavourite("Bearer $token", movieId) }
                .onSuccess {
                    _favouriteState.postValue(true)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }

    fun deleteFavourite(token: String, movieId: MovieIdModel) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            kotlin.runCatching { response.deleteFavourite("Bearer $token", movieId) }
                .onSuccess {
                    _favouriteState.postValue(false)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}