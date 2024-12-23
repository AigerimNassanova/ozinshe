package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val id: Int, // 25805
    @SerializedName("username")
    val username: String, // aigerim.nassanova@gmail.com
    @SerializedName("email")
    val email: String, // aigerim.nassanova@gmail.com
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("accessToken")
    val accessToken: String, // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhaWdlcmltLm5hc3Nhbm92YUBnbWFpbC5jb20iLCJpYXQiOjE3MjU5NTg5MTAsImV4cCI6MTc1NzQ5NDkxMH0.lg_IFIzWzFd4HZnz1dzE4AENVtj1engvwNbAW5Vu3VCUBqju4hQn7WdbRQmh4DrGklzvLoo2xjjUHNQCAgYN_g
    @SerializedName("tokenType")
    val tokenType: String // Bearer
)