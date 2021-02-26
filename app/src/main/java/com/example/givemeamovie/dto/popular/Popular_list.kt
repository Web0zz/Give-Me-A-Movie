package com.example.givemeamovie.dto.popular

data class Popular_list(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)