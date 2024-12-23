package com.example.ozinshe.presentation.profile.changePassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import com.example.ozinshe.domain.model.ResetPasswordRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChangePasswordViewModel(): ViewModel() {
    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    private var _responseSuccess = MutableLiveData<String>()
    val responseSuccess: MutableLiveData<String> = _responseSuccess

    fun resetPassword(token: String, newPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            kotlin.runCatching { response.resetPassword("Bearer $token", ResetPasswordRequest(newPassword)) }
                .onSuccess {
                    _responseSuccess.postValue(it.toString())
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}