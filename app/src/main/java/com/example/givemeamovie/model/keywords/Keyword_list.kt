package com.example.givemeamovie.model.keywords

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Keywords(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "keywords") val keywords: List<Keyword>
)