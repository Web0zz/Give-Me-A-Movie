package com.web0zz.givemeamovie.model.network.movie_lists

import com.web0zz.givemeamovie.model.entity.Movie

data class MovieListSection(
    val section_title: String,
    val movies: List<Movie>
)
