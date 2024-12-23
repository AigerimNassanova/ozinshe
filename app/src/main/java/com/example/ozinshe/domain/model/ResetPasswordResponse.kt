package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class ResetPasswordResponse(
    @SerializedName("id")
    val id: Int, // 25805
    @SerializedName("username")
    val username: String, // aigerim.nassanova@gmail.com
    @SerializedName("email")
    val email: String, // aigerim.nassanova@gmail.com
    @SerializedName("roles")
    val roles: List<String>,
    @SerializedName("accessToken")
    val accessToken: String, // eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhaWdlcmltLm5hc3Nhbm92YUBnbWFpbC5jb20iLCJpYXQiOjE3MzI4ODE5NjgsImV4cCI6MTc2NDQxNzk2OH0.HEmfFPHtEddXAyakx4Ra5FTbGxhMlmFGKJN0WOxKi14HrBndZMH3Wjxd7LvNt27BKBfVmdm_uAJdhP5t8ee89g
    @SerializedName("tokenType")
    val tokenType: String // Bearer
)