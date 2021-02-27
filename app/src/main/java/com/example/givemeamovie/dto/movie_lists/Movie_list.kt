package com.example.givemeamovie.dto.movie_lists

data class Movie_list(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
