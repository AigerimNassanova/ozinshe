package com.example.ozinshe.presentation.profile.editProfile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import com.example.ozinshe.domain.model.UserInfoRequest
import com.example.ozinshe.domain.model.UserInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditProfileViewModel(): ViewModel() {
    private var _userInfoResponse: MutableLiveData<UserInfoResponse> = MutableLiveData()
    val userInfoResponse: LiveData<UserInfoResponse> = _userInfoResponse

    private var _postUserInfoResponse: MutableLiveData<UserInfoResponse> = MutableLiveData()
    val postUserInfoResponse: LiveData<UserInfoResponse> = _postUserInfoResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    fun getUserInfo(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            kotlin.runCatching { response.getUserInfo("Bearer $token") }
                .onSuccess {
                    _userInfoResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }

    fun updateUserInfo(token: String, requestBody: UserInfoRequest) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            kotlin.runCatching { response.postUserInfo("Bearer $token", requestBody) }
                .onSuccess {
                    _postUserInfoResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }


}