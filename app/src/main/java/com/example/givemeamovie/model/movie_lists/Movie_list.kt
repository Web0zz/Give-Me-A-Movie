package com.example.givemeamovie.model.movie_lists

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie_list(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val results: List<Movie>,
    @field:Json(name = "total_pages") val total_pages: Int,
    @field:Json(name = "total_results") val total_results: Int
)
