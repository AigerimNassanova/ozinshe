package com.example.ozinshe.domain.model


import com.google.gson.annotations.SerializedName

data class MoviesByCategoryMainModelItem(
    @SerializedName("categoryId")
    val categoryId: Int, // 1
    @SerializedName("categoryName")
    val categoryName: String, // ÖZINŞE–де танымал
    @SerializedName("movies")
    val movies: List<Movy>
)