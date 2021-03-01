package com.example.givemeamovie.model.network.credits

data class Cast_and_Crew(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)