package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class UserInfoResponse(
    @SerializedName("birthDate")
    val birthDate: String, // 2024-11-28
    @SerializedName("id")
    val id: Int, // 0
    @SerializedName("language")
    val language: String, // string
    @SerializedName("name")
    val name: String, // string
    @SerializedName("phoneNumber")
    val phoneNumber: String, // string
    @SerializedName("user")
    val user: User
)