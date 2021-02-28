package com.example.givemeamovie.model.movie_detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Genre(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String
)