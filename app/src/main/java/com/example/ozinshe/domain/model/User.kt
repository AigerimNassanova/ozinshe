package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    val email: String, // string
    @SerializedName("id")
    val id: Int // 0
)