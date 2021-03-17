package com.web0zz.givemeamovie.repository

import com.web0zz.givemeamovie.data.remote.Resource
import com.web0zz.givemeamovie.data.remote.service.MovieListService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HomeRepository @Inject constructor(
        private val movieListService: MovieListService,
): Repository {

    fun fetchSearchMovie(enteredString: String, page: Int = 1) = flow {
        val movies = Resource.toResource { movieListService.fetchSearchMovie(enteredString, page) }
        emit(movies)
    }.flowOn(Dispatchers.IO)

    fun fetchNowPlayingMovie(page: Int = 1) = flow {
        val movies = Resource.toResource { movieListService.fetchNowPlayingMovie(page) }
        emit(movies)
    }.flowOn(Dispatchers.IO)

    fun fetchPopularMovies(page: Int = 1) = flow {
        val movies = Resource.toResource { movieListService.fetchPopularMovie(page) }
        emit(movies)
    }.flowOn(Dispatchers.IO)

    fun fetchTopRatedMovies(page: Int = 1) = flow {
        val movies = Resource.toResource { movieListService.fetchTopRatedMovie(page) }
        emit(movies)
    }.flowOn(Dispatchers.IO)
}