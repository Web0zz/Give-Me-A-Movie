package com.example.givemeamovie.dto.credits

data class Cast_and_Crew(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)