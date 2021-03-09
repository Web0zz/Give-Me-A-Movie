package com.example.givemeamovie.model.entity

import androidx.room.Entity

@Entity(primaryKeys = ["movieId"])
data class RecommendMovie(
        val movieId: Int
)
