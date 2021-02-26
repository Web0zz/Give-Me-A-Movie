package com.example.givemeamovie.dto.search

data class Search_list(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)