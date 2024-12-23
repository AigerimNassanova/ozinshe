package com.example.ozinshe.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import com.example.ozinshe.domain.model.UserInfoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.Language

class ProfileViewModel(): ViewModel() {
    private var _userInfoResponse: MutableLiveData<UserInfoResponse> = MutableLiveData()
    val userInfoResponse: LiveData<UserInfoResponse> = _userInfoResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    private var _language: MutableLiveData<String> = MutableLiveData()
    val language: LiveData<String> = _language

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

    fun selectLanguage(language: String){
        _language.postValue(language)
    }
}