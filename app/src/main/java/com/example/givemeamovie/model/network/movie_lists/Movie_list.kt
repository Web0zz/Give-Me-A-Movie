package com.example.givemeamovie.model.network.movie_lists

import com.example.givemeamovie.model.entity.Movie

data class Movie_list(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
