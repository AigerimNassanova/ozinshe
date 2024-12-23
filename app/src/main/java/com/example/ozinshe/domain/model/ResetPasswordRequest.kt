package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class ResetPasswordRequest(
    @SerializedName("password")
    val password: String // string
)