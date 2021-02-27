package com.example.givemeamovie.model.movie_detail

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Detail(
    @field:Json(name = "adult") val adult: Boolean,
    @field:Json(name = "backdrop_path") val backdrop_path: String,
    @field:Json(name = "belongs_to_collection") val belongs_to_collection: Any,
    @field:Json(name = "budget") val budget: Int,
    @field:Json(name = "genres") val genres: List<Genre>,
    @field:Json(name = "homepage") val homepage: String,
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "imdb_id") val imdb_id: String,
    @field:Json(name = "original_language") val original_language: String,
    @field:Json(name = "original_title") val original_title: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "popularity") val popularity: Double,
    @field:Json(name = "poster_path") val poster_path: String,
    @field:Json(name = "production_companies") val production_companies: List<ProductionCompany>,
    @field:Json(name = "production_countries") val production_countries: List<ProductionCountry>,
    @field:Json(name = "release_date") val release_date: String,
    @field:Json(name = "revenue") val revenue: Int,
    @field:Json(name = "runtime") val runtime: Int,
    @field:Json(name = "spoken_languages") val spoken_languages: List<SpokenLanguage>,
    @field:Json(name = "status") val status: String,
    @field:Json(name = "tagline") val tagline: String,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "video") val video: Boolean,
    @field:Json(name = "vote_average") val vote_average: Double,
    @field:Json(name = "vote_count") val vote_count: Int
)