package com.example.givemeamovie.repository

import com.example.givemeamovie.data.local.LibraryDao
import com.example.givemeamovie.data.local.MovieDao
import com.example.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary
import com.example.givemeamovie.model.entity.MovieLibraryCrossRef
import javax.inject.Inject

class MovieWatchListRepository  @Inject constructor(
        private val movieLibraryDao: LibraryDao,
        private val movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao,
        private val movieDao: MovieDao
): Repository {

    fun insertNewLibrary(library_name: String) {
        movieLibraryDao.addNewMovieLibrary(MovieLibrary(library_name))
    }

    fun insertMovie(movie: Movie) {
        movieDao.insertMovieList(listOf(movie))
    }

    fun insertMovieToLibrary(movie: Movie, library_name: String) {
        movieLibraryWithMoviesDao.insert(MovieLibraryCrossRef(library_name, movie.movie_id))
    }

    fun getAvailabeleLibraries(): List<MovieLibrary> {
        return movieLibraryDao.getAvailableLibraries()
    }

    fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

    fun deleteMovieFromLibrary(movie: Movie, library_name: String) {
        movieLibraryWithMoviesDao.deleteMovie(MovieLibraryCrossRef(library_name, movie.movie_id))
    }
}