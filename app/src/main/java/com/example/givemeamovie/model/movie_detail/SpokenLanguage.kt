package com.example.givemeamovie.model.movie_detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpokenLanguage(
    @field:Json(name = "english_name") val english_name: String,
    @field:Json(name = "iso_639_1") val iso_639_1: String,
    @field:Json(name = "name") val name: String
)