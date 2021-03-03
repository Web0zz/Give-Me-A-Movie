package com.example.givemeamovie.data.remote.client

import com.example.givemeamovie.data.remote.NetworkResponse
import com.example.givemeamovie.data.remote.service.MovieDetailService
import com.example.givemeamovie.data.remote.transform
import com.example.givemeamovie.model.network.credits.Cast_and_Crew
import com.example.givemeamovie.model.network.keywords.Keyword_List
import com.example.givemeamovie.model.network.movie_detail.Detail
import com.example.givemeamovie.model.network.movie_detail.Video

class NetworkClient (private val service: MovieDetailService){

    fun fetchMovieDetail(
            movie_id: Int,
            forResult: (response: NetworkResponse<Detail>) -> Unit
    ) {
        this.service.fetchMovieDetail(movie_id).transform(forResult)
    }

    fun fetchMovieCast(
            movie_id: Int,
            forResult: (response: NetworkResponse<Cast_and_Crew>) -> Unit
    ) {
        this.service.fetchMovieCast(movie_id).transform(forResult)
    }

    fun fetchVideos(
            movie_id: Int,
            forResult: (response: NetworkResponse<Video>) -> Unit
    ) {
        this.service.fetchVideos(movie_id).transform(forResult)
    }

    fun fetchKeywords(
            movie_id: Int,
            forResult: (response: NetworkResponse<Keyword_List>) -> Unit
    ) {
        this.service.fetchKeywords(movie_id).transform(forResult)
    }
}