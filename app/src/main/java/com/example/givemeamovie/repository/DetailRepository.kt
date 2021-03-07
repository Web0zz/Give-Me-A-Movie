package com.example.givemeamovie.repository

import com.example.givemeamovie.data.remote.Resource
import com.example.givemeamovie.data.remote.service.MovieDetailService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val movieDetailService: MovieDetailService,
): Repository {

    fun fetchMovieDetail(movie_id: Int) = flow {
        val detail = Resource.toResource { movieDetailService.fetchMovieDetail(movie_id) }
        emit(detail)
    }.flowOn(Dispatchers.IO)

    fun fetchMovieCast(movie_id: Int) = flow {
        val cast = Resource.toResource { movieDetailService.fetchMovieCast(movie_id) }
        emit(cast)
    }.flowOn(Dispatchers.IO)

    fun fetchVideos(movie_id: Int) = flow {
        val video = Resource.toResource { movieDetailService.fetchVideos(movie_id) }
        emit(video)
    }.flowOn(Dispatchers.IO)

    fun fetchKeywords(movie_id: Int) = flow {
        val keywords = Resource.toResource { movieDetailService.fetchKeywords(movie_id) }
        emit(keywords)
    }.flowOn(Dispatchers.IO)
}