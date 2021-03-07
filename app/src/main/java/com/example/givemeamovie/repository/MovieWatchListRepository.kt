package com.example.givemeamovie.repository

import com.example.givemeamovie.data.local.LibraryDao
import com.example.givemeamovie.data.local.MovieDao
import com.example.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary
import com.example.givemeamovie.model.entity.MovieLibraryCrossRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieWatchListRepository  @Inject constructor(
        private val movieLibraryDao: LibraryDao,
        private val movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao,
        private val movieDao: MovieDao
): Repository {

    fun getAvailabeleLibraries() = flow {
        val movieLibraries = movieLibraryDao.getAvailableLibraries()
        emit(movieLibraries)
    }.flowOn(Dispatchers.IO)

    suspend fun insertNewLibrary(library_name: String) {
        movieLibraryDao.addNewMovieLibrary(MovieLibrary(library_name))
    }

    suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovieList(listOf(movie))
    }

    suspend fun insertMovieToLibrary(movie: Movie, library_name: String) {
        movieLibraryWithMoviesDao.insert(MovieLibraryCrossRef(library_name, movie.movie_id))
    }

    suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

    suspend fun deleteMovieFromLibrary(movie: Movie, library_name: String) {
        movieLibraryWithMoviesDao.deleteMovie(MovieLibraryCrossRef(library_name, movie.movie_id))
    }
}