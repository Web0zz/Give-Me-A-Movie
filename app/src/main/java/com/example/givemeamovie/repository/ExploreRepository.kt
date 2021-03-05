package com.example.givemeamovie.repository

import com.example.givemeamovie.data.local.MovieDao
import com.example.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.example.givemeamovie.data.remote.Resource
import com.example.givemeamovie.data.remote.service.MovieListService
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibraryCrossRef
import com.example.givemeamovie.model.network.movie_lists.Movie_list
import javax.inject.Inject

class ExploreRepository @Inject constructor(
        private val movieListService: MovieListService,
        private val movieDao: MovieDao,
        private val croffRefDao: MovieLibraryWithMoviesDao,
): Repository {

    suspend fun getRecommendedMovies(movie_id: Int, page: Int = 1): Resource<Movie_list> {
        return Resource.toResource { movieListService.fetchRecommendationMovie(movie_id, page) }
    }

    fun addMovieToDatabase(movie: Movie, library_name: String) {
        movieDao.insertMovieList(listOf(movie))
        croffRefDao.insert(MovieLibraryCrossRef(library_name, movie.movie_id))
    }
}