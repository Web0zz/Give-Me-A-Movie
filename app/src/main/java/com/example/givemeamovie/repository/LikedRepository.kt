package com.example.givemeamovie.repository

import com.example.givemeamovie.data.local.LibraryDao
import com.example.givemeamovie.data.local.MovieDao
import com.example.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary
import com.example.givemeamovie.model.entity.MovieLibraryCrossRef
import javax.inject.Inject

class LikedRepository @Inject constructor(
        private val movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao,
        private val movieLibraryDao: LibraryDao,
        private val movieDao: MovieDao
): Repository {

    private fun getMovie(movie_id: Int): Movie {
        return movieDao.getMovie(movie_id)
    }

    private fun getMoviesRefFromLibrary(libraryName: String): List<MovieLibraryCrossRef> {
        return movieLibraryWithMoviesDao.getMovies(libraryName)
    }

    fun getMoviesInLibrary(libraryName: String) : List<Movie> {
        val movies = mutableListOf<Movie>()
        getMoviesRefFromLibrary(libraryName).map {
           movies.add(getMovie(it.movie_id))
        }
        return movies
    }

    fun getAvailableLibraries(): List<MovieLibrary> {
        return movieLibraryDao.getAvailableLibraries()
    }
}