package com.web0zz.givemeamovie.data.remote


object Network {
    private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w500"
    private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w600"

    @JvmStatic
    fun getPosterURL(poster_path: String?): String {
        return BASE_POSTER_PATH + poster_path
    }

    @JvmStatic
    fun getBackdropURL(backdrop_path: String?): String {
        return BASE_BACKDROP_PATH + backdrop_path
    }
}