package com.example.givemeamovie.model.credits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cast(
    @field:Json(name = "adult") val adult: Boolean,
    @field:Json(name = "cast_id") val cast_id: Int,
    @field:Json(name = "character") val character: String,
    @field:Json(name = "credit_id") val credit_id: String,
    @field:Json(name = "gender") val gender: Int,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "known_for_department") val known_for_department: String,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "order") val order: Int,
    @field:Json(name = "original_name") val original_name: String,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "profile_path") val profile_path: String
)