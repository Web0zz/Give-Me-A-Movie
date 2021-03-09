package com.example.givemeamovie.repository

import com.example.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.example.givemeamovie.data.remote.Resource
import com.example.givemeamovie.data.remote.service.MovieDetailService
import com.example.givemeamovie.model.entity.MovieLibraryCrossRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
        private val movieDetailService: MovieDetailService,
        private val movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao
): Repository {

    suspend fun addToLibrary(crossRef: MovieLibraryCrossRef) {
        movieLibraryWithMoviesDao.insert(crossRef)
    }
    suspend fun deleteFromLibrary(crossRef: MovieLibraryCrossRef) {
        movieLibraryWithMoviesDao.deleteMovie(crossRef)
    }
    suspend fun checkIsThere(crossRef: MovieLibraryCrossRef): Boolean {
        return movieLibraryWithMoviesDao.checkIsThere(crossRef.movie_id, crossRef.library_Name)
    }

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

    fun fetchSimilarMovies(movie_id: Int) = flow {
        val similar = Resource.toResource { movieDetailService.fetchSimilarMovie(movie_id,1) }
        emit(similar)
    }.flowOn(Dispatchers.IO)
}