package com.example.givemeamovie.data.remote

object Network {
    private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w500"
    private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w600"
    private const val YOUTUBE_VIDEO_URL = "https://www.youtube.com/watch?v="

    @JvmStatic
    fun getPosterURL(poster_path: String?): String {
        return BASE_POSTER_PATH + poster_path
    }

    @JvmStatic
    fun getBackdropURL(backdrop_path: String?): String {
        return BASE_BACKDROP_PATH + backdrop_path
    }

    @JvmStatic
    fun getVideoURL(video_path: String?): String {
        return YOUTUBE_VIDEO_URL + video_path
    }
}