package com.example.givemeamovie.model.entity

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(primaryKeys = ["movie_id"])
data class Movie(
        val adult: Boolean,
        val backdrop_path: String,
        val genre_ids: List<Int>,
        @SerializedName("id")
        val movie_id: Int,
        val original_language: String,
        val original_title: String,
        val overview: String,
        val popularity: Double,
        val poster_path: String,
        val release_date: String,
        val title: String,
        val video: Boolean,
        val vote_average: Double,
        val vote_count: Int
)
