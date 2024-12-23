package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class PosterX(
    @SerializedName("id")
    val id: Int, // 145
    @SerializedName("link")
    val link: String, // http://api.ozinshe.com/core/public/V1/show/669
    @SerializedName("fileId")
    val fileId: Int, // 669
    @SerializedName("movieId")
    val movieId: Int // 124
)