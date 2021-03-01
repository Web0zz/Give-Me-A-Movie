package com.example.givemeamovie.model.entity

import androidx.room.Entity

@Entity(primaryKeys = ["library_Name", "movie_id"])
data class MovieLibraryCrossRef(
         val library_Name: String,
         val movie_id: Int
)
