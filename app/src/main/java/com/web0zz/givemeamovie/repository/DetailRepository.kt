package com.web0zz.givemeamovie.repository

import com.web0zz.givemeamovie.data.local.MovieDao
import com.web0zz.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.web0zz.givemeamovie.data.remote.Resource
import com.web0zz.givemeamovie.data.remote.service.MovieDetailService
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.MovieLibraryCrossRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DetailRepository @Inject constructor(
        private val movieDetailService: MovieDetailService,
        private val movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao,
        private val movieDao: MovieDao
): Repository {

    suspend fun addToLibrary(crossRef: MovieLibraryCrossRef, movie: Movie) {
        movieLibraryWithMoviesDao.insert(crossRef)
        movieDao.insertMovieList(listOf(movie))
    }
    suspend fun deleteFromLibrary(crossRef: MovieLibraryCrossRef, movie: Movie) {
        movieLibraryWithMoviesDao.deleteMovie(crossRef)
        movieDao.deleteMovie(movie)
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