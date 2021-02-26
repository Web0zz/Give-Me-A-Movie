package com.example.givemeamovie.dto.top

data class Top_list(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)