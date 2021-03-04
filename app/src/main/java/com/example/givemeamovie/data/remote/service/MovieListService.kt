package com.example.givemeamovie.data.remote.service

import com.example.givemeamovie.model.network.movie_lists.Movie_list
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieListService {

    @GET("movie/{movie_id}/similar?language=en-US&page={page}")
    suspend fun fetchSimilarMovie(
            @Path("movie_id") movie_id: Int,
            @Path("page") page: Int
    ): Response<Movie_list>

    @GET("movie/{movie_id}/recommendations?language=en-US&page={page}")
    suspend fun fetchRecommendationMovie(
            @Path("movie_id") movie_id: Int,
            @Path("page") page: Int
    ): Response<Movie_list>

    @GET("search/movie?language=en-US&query={query}&page={page}&include_adult=false")
    suspend fun fetchSearchMovie(
            @Path("query") query: String,
            @Path("page") page: Int
    ): Response<Movie_list>

    @GET("movie/now_playing?language=en-US&page={page}")
    suspend fun fetchNowPlayingMovie(
            @Path("page") page: Int
    ): Response<Movie_list>

    @GET("movie/popular?language=en-US&page={page}")
    suspend fun fetchPopularMovie(
            @Path("page") page: Int
    ): Response<Movie_list>

    @GET("movie/top_rated?language=en-US&page={page}")
    suspend fun fetchTopRatedMovie(
            @Path("page") page: Int
    ): Response<Movie_list>
}