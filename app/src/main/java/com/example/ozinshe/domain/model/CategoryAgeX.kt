package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class CategoryAgeX(
    @SerializedName("id")
    val id: Int, // 5
    @SerializedName("name")
    val name: String, // 16-18
    @SerializedName("fileId")
    val fileId: Int, // 358
    @SerializedName("link")
    val link: String, // http://api.ozinshe.com/core/public/V1/show/358
    @SerializedName("movieCount")
    val movieCount: Any // null
)