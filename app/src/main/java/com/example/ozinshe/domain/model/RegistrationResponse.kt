package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("id")
    val id: Int, // 25806
    @SerializedName("username")
    val username: String, // aigerim.nassanova2@gmail.com
    @SerializedName("email")
    val email: String, // aigerim.nassanova2@gmail.com
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("accessToken")
    val accessToken: String, // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhaWdlcmltLm5hc3Nhbm92YTJAZ21haWwuY29tIiwiaWF0IjoxNzI1OTYxODk1LCJleHAiOjE3NTc0OTc4OTV9.RgeitMc1EdRbl7_8ed2zF1OZLiXC7dd8DBoZpMPClfuUHlfnUNj3AfgXMWkMsLsdfkLzWhqOxfQZiOfeklUZ_Q
    @SerializedName("tokenType")
    val tokenType: String // Bearer
)