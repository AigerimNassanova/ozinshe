package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    @SerializedName("email")
    val email: String, // aigerim.nassanova2@gmail.com
    @SerializedName("password")
    val password: String // aika123
)