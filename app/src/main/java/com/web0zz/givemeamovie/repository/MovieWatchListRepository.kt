package com.web0zz.givemeamovie.repository

import com.web0zz.givemeamovie.data.local.LibraryDao
import com.web0zz.givemeamovie.data.local.MovieDao
import com.web0zz.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.MovieLibrary
import com.web0zz.givemeamovie.model.entity.MovieLibraryCrossRef
import javax.inject.Inject

class MovieWatchListRepository @Inject constructor(
    private val movieLibraryDao: LibraryDao,
    private val movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao,
    private val movieDao: MovieDao
) : Repository {

    suspend fun getAvailableLibraries(): List<MovieLibrary> {
        return movieLibraryDao.getAvailableLibraries()
    }

    suspend fun insertNewLibrary(library_name: String, first_movie: Movie) {
        movieLibraryDao.addNewMovieLibrary(MovieLibrary(library_name, 1, first_movie.backdrop_path))
    }

    suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovieList(listOf(movie))
    }

    suspend fun insertMovieToLibrary(movie: Movie, library_name: String) {
        movieLibraryWithMoviesDao.insert(MovieLibraryCrossRef(library_name, movie.movie_id))
        movieLibraryDao.updateLibrarySize(1, library_name)
    }

    suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteMovie(movie)
    }

    suspend fun deleteMovieFromLibrary(movie: Movie, library_name: String) {
        movieLibraryWithMoviesDao.deleteMovie(MovieLibraryCrossRef(library_name, movie.movie_id))
        movieLibraryDao.updateLibrarySize(-1, library_name)
    }
}
