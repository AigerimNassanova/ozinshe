package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class UserInfoRequest(
    @SerializedName("birthDate")
    val birthDate: String, // 2004-11-13
    @SerializedName("id")
    val id: Int, // 25777
    @SerializedName("language")
    val language: String, // string
    @SerializedName("name")
    val name: String, // Aigerim Nassanova
    @SerializedName("phoneNumber")
    val phoneNumber: String // +77085682145
)