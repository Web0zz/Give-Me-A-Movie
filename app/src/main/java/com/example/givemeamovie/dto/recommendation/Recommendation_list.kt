package com.example.givemeamovie.dto.recommendation

data class Recommendation_list(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)