package com.example.givemeamovie.repository

import androidx.annotation.WorkerThread
import com.example.givemeamovie.data.local.LibraryDao
import com.example.givemeamovie.data.local.MovieDao
import com.example.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibraryCrossRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
        private val movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao,
        private val movieLibraryDao: LibraryDao,
        private val movieDao: MovieDao
): Repository {

    private suspend fun getMovie(movie_id: Int): Movie {
        return movieDao.getMovie(movie_id)
    }

    private suspend fun getMoviesRefFromLibrary(libraryName: String): List<MovieLibraryCrossRef>  {
        return movieLibraryWithMoviesDao.getMovies(libraryName)
    }

    fun getMoviesInLibrary(
            libraryName: String,
            onError: (String?) -> Unit
    ) = flow {
        try {
            val movies = mutableListOf<Movie>()
            getMoviesRefFromLibrary(libraryName).map {
                movies.add(getMovie(it.movie_id))
            }
            emit(movies)
        } catch (ex: Throwable) {
            onError(ex.localizedMessage)
        }
    }.flowOn(Dispatchers.IO)

    fun getAvailableLibraries() = flow {
        val movie_Libraries = movieLibraryDao.getAvailableLibraries()
        emit(movie_Libraries)
    }.flowOn(Dispatchers.IO)
}