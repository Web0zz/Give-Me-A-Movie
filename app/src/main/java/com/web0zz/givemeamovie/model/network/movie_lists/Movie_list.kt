package com.web0zz.givemeamovie.model.network.movie_lists

import com.web0zz.givemeamovie.model.entity.Movie

data class Movie_list(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
