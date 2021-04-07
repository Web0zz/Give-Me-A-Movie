package com.web0zz.givemeamovie.repository

import com.web0zz.givemeamovie.data.local.LibraryDao
import com.web0zz.givemeamovie.data.local.MovieDao
import com.web0zz.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.MovieLibraryCrossRef
import javax.inject.Inject

class FavoriteRepository @Inject constructor(
    private val movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao,
    private val movieLibraryDao: LibraryDao,
    private val movieDao: MovieDao
) : Repository {

    private suspend fun getMovie(movie_id: Int): Movie? {
        return movieDao.getMovie(movie_id)
    }

    private suspend fun getMoviesRefFromLibrary(libraryName: String): List<MovieLibraryCrossRef> {
        return movieLibraryWithMoviesDao.getMovies(libraryName)
    }

    suspend fun getMoviesInLibrary(
        libraryName: String
    ): List<Movie> {
        val movies = mutableListOf<Movie>()
        getMoviesRefFromLibrary(libraryName).map {
            val movie = getMovie(it.movie_id)
            if (movie != null) getMovie(movie.movie_id)
        }
        return movies
    }

    suspend fun getAvailableLibraries() = movieLibraryDao.getAvailableLibraries()
}
