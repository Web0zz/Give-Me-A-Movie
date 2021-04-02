package com.web0zz.givemeamovie.data.remote.service

import com.web0zz.givemeamovie.model.network.movie_lists.Movie_list
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieListService {

    @GET("movie/{movie_id}/recommendations?language=en-US")
    suspend fun fetchRecommendationMovie(
        @Query("movie_id") movie_id: Int,
        @Query("page") page: Int
    ): Response<Movie_list>

    @GET("search/movie?language=en-US&include_adult=false")
    suspend fun fetchSearchMovie(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<Movie_list>

    @GET("movie/now_playing?language=en-US")
    suspend fun fetchNowPlayingMovie(
        @Query("page") page: Int
    ): Response<Movie_list>

    @GET("movie/popular?language=en-US")
    suspend fun fetchPopularMovie(
        @Query("page") page: Int
    ): Response<Movie_list>

    @GET("movie/top_rated?language=en-US")
    suspend fun fetchTopRatedMovie(
        @Query("page") page: Int
    ): Response<Movie_list>
}
