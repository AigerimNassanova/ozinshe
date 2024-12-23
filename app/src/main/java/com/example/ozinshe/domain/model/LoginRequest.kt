package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email")
    val email: String, // aigerim.nassanova@gmail.com
    @SerializedName("password")
    val password: String // aika123
)