package com.example.givemeamovie.data.remote.client

import com.example.givemeamovie.data.remote.NetworkResponse
import com.example.givemeamovie.data.remote.service.MovieListService
import com.example.givemeamovie.data.remote.transform
import com.example.givemeamovie.model.network.movie_lists.Movie_list

class MovieListClient (private val service: MovieListService) {

    fun fetchSimilarMovie(
            movie_id: Int ,
            page: Int = 1,
            forResult: (response: NetworkResponse<Movie_list>) -> Unit
    ) {
        this.service.fetchSimilarMovie(movie_id, page).transform(forResult)
    }

    fun fetchRecommendationMovie(
            movie_id: Int,
            page: Int = 1,
            forResult: (response: NetworkResponse<Movie_list>) -> Unit
    ) {
        this.service.fetchRecommendationMovie(movie_id, page).transform(forResult)
    }

    fun fetchSearchMovie(
            query: String,
            page: Int = 1,
            forResult: (response: NetworkResponse<Movie_list>) -> Unit
    ) {
        this.service.fetchSearchMovie(query, page).transform(forResult)
    }

    fun fetchNowPlayingMovie(
            page: Int = 1,
            forResult: (response: NetworkResponse<Movie_list>) -> Unit
    ) {
        this.service.fetchNowPlayingMovie(page).transform(forResult)
    }

    fun fetchPopularMovie(
            page: Int = 1,
            forResult: (response: NetworkResponse<Movie_list>) -> Unit
    ) {
        this.service.fetchPopularMovie(page).transform(forResult)
    }

    fun fetchTopRatedMovie(
            page: Int = 1,
            forResult: (response: NetworkResponse<Movie_list>) -> Unit
    ) {
        this.service.fetchTopRatedMovie(page).transform(forResult)
    }
}