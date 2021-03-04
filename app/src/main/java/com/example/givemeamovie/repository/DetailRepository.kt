package com.example.givemeamovie.repository

import com.example.givemeamovie.data.remote.Resource
import com.example.givemeamovie.data.remote.service.MovieDetailService
import com.example.givemeamovie.model.network.credits.Cast_and_Crew
import com.example.givemeamovie.model.network.keywords.Keyword_List
import com.example.givemeamovie.model.network.movie_detail.Detail
import com.example.givemeamovie.model.network.movie_detail.Video
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val movieDetailService: MovieDetailService
): Repository {

    suspend fun fetchMovieDetail(
            movie_id: Int
    ): Resource<Detail> {
        return Resource.toResource { movieDetailService.fetchMovieDetail(movie_id) }
    }

    suspend fun fetchMovieCast(
            movie_id: Int
    ): Resource<Cast_and_Crew> {
        return Resource.toResource { movieDetailService.fetchMovieCast(movie_id) }
    }

    suspend fun fetchVideos(
            movie_id: Int
    ): Resource<Video> {
        return Resource.toResource { movieDetailService.fetchVideos(movie_id) }
    }

    suspend fun fetchKeywords(
            movie_id: Int
    ): Resource<Keyword_List> {
        return Resource.toResource { movieDetailService.fetchKeywords(movie_id) }
    }
}