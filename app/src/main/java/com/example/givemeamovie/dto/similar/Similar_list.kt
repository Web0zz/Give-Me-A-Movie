package com.example.givemeamovie.dto.similar

data class Similar_list(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)