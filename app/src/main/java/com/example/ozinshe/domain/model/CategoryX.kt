package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class CategoryX(
    @SerializedName("id")
    val id: Int, // 1
    @SerializedName("name")
    val name: String, // ÖZINŞE–де танымал
    @SerializedName("fileId")
    val fileId: Any, // null
    @SerializedName("link")
    val link: String, // http://api.ozinshe.com/core/public/V1/show/null
    @SerializedName("movieCount")
    val movieCount: Int // 16
)