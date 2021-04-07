package com.web0zz.givemeamovie.data.remote.service

import com.web0zz.givemeamovie.model.network.credits.Cast_and_Crew
import com.web0zz.givemeamovie.model.network.movie_detail.Detail
import com.web0zz.givemeamovie.model.network.movie_detail.Video
import com.web0zz.givemeamovie.model.network.movie_lists.Movie_list
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDetailService {

    @GET("movie/{movie_id}?language=en-US")
    suspend fun fetchMovieDetail(
        @Path("movie_id") movie_id: Int
    ): Response<Detail>

    @GET("movie/{movie_id}/credits?language=en-US")
    suspend fun fetchMovieCast(
        @Path("movie_id") movie_id: Int
    ): Response<Cast_and_Crew>

    @GET("movie/{movie_id}/videos?language=en-US")
    suspend fun fetchVideos(
        @Path("movie_id") movie_id: Int
    ): Response<Video>

    @GET("movie/{movie_id}/similar?language=en-US")
    suspend fun fetchSimilarMovie(
        @Path("movie_id") movie_id: Int,
        @Query("page") page: Int
    ): Response<Movie_list>
}
