package com.example.givemeamovie.model.entity

import androidx.room.Entity

@Entity(primaryKeys = ["library_Name"])
data class MovieLibrary(
    val library_Name: String,
)
