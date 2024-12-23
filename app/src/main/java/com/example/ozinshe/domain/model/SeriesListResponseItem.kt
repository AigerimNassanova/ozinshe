package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class SeriesListResponseItem(
    @SerializedName("id")
    val id: Int, // 134
    @SerializedName("movieId")
    val movieId: Int, // 121
    @SerializedName("number")
    val number: Int, // 1
    @SerializedName("videos")
    val videos: List<VideoXXX>
)