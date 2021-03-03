package com.example.givemeamovie.data.remote.service

import com.example.givemeamovie.model.network.credits.Cast_and_Crew
import com.example.givemeamovie.model.network.keywords.Keyword_List
import com.example.givemeamovie.model.network.movie_detail.Detail
import com.example.givemeamovie.model.network.movie_detail.Video
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailService {

    @GET("movie/{movie_id}?language=en-US")
    fun fetchMovieDetail(
            @Path("movie_id") movie_id: Int
    ): Call<Detail>

    @GET("movie/{movie_id}/credits?language=en-US")
    fun fetchMovieCast(
            @Path("movie_id") movie_id: Int
    ): Call<Cast_and_Crew>

    @GET("movie/{movie_id}/videos?language=en-US")
    fun fetchVideos(
            @Path("movie_id") movie_id: Int
    ): Call<Video>

    @GET("movie/{movie_id}/keywords?")
    fun fetchKeywords(
            @Path("movie_id") movie_id: Int
    ): Call<Keyword_List>
}