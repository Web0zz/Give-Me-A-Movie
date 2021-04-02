package com.web0zz.givemeamovie.repository

import com.web0zz.givemeamovie.data.remote.Resource
import com.web0zz.givemeamovie.data.remote.service.MovieListService
import com.web0zz.givemeamovie.model.network.movie_lists.Movie_list
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val movieListService: MovieListService,
) : Repository {

    suspend fun fetchSearchMovie(enteredString: String, page: Int = 1): Resource<Movie_list> {
        return Resource.toResource { movieListService.fetchSearchMovie(enteredString, page) }
    }

    suspend fun fetchNowPlayingMovie(page: Int = 1): Resource<Movie_list> {
        return Resource.toResource { movieListService.fetchNowPlayingMovie(page) }
    }

    suspend fun fetchPopularMovies(page: Int = 1): Resource<Movie_list> {
        return Resource.toResource { movieListService.fetchPopularMovie(page) }
    }

    suspend fun fetchTopRatedMovies(page: Int = 1): Resource<Movie_list> {
        return Resource.toResource { movieListService.fetchTopRatedMovie(page) }
    }
}
