package com.example.ozinshe.presentation.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ozinshe.data.ApiService
import com.example.ozinshe.data.ServiceBuilder
import com.example.ozinshe.domain.model.RegistrationRequest
import com.example.ozinshe.domain.model.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegistrationViewModel(): ViewModel() {

    private var _registrationResponse: MutableLiveData<RegistrationResponse> = MutableLiveData()
    val registrationResponse: LiveData<RegistrationResponse> = _registrationResponse

    private var _errorResponse: MutableLiveData<String> = MutableLiveData()
    val errorResponse: LiveData<String> = _errorResponse

    fun registrationUser(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = ServiceBuilder.buildService(ApiService::class.java)
            kotlin.runCatching { response.registrationUser(RegistrationRequest(email, password)) }
                .onSuccess {
                    _registrationResponse.postValue(it)
                }
                .onFailure {
                    _errorResponse.postValue(it.message)
                }
        }
    }
}