package com.example.givemeamovie.model.keywords

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Keyword(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "name") val name: String
)