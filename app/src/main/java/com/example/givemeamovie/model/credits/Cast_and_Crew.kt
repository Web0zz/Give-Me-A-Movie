package com.example.givemeamovie.model.credits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Cast_and_Crew(
    @field:Json(name = "cast") val cast: List<Cast>,
    @field:Json(name = "crew") val crew: List<Crew>,
    @field:Json(name = "id")  val id: Int
)