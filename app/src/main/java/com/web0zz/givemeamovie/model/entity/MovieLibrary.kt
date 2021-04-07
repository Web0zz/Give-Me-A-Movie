package com.web0zz.givemeamovie.model.entity

import androidx.room.Entity

@Entity(primaryKeys = ["library_Name"])
data class MovieLibrary(
    val library_Name: String,
    val library_size: Int = 1,
    val library_poster_path: String
)
