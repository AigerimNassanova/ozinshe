package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class CategoryXX(
    @SerializedName("id")
    val id: Int, // 8
    @SerializedName("name")
    val name: String, // Толықметрлі мультфильмдер
    @SerializedName("fileId")
    val fileId: Any, // null
    @SerializedName("link")
    val link: Any, // null
    @SerializedName("movieCount")
    val movieCount: Any // null
)