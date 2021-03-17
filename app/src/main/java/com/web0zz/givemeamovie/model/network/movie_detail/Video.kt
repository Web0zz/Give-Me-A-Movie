package com.web0zz.givemeamovie.model.network.movie_detail

data class Video(
    val id: Int,
    val results: List<Video_Result>
)