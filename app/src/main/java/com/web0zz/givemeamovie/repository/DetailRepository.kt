package com.web0zz.givemeamovie.repository

import com.web0zz.givemeamovie.data.local.MovieDao
import com.web0zz.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.web0zz.givemeamovie.data.remote.Resource
import com.web0zz.givemeamovie.data.remote.service.MovieDetailService
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.MovieLibraryCrossRef
import com.web0zz.givemeamovie.model.network.credits.Cast_and_Crew
import com.web0zz.givemeamovie.model.network.movie_detail.Detail
import com.web0zz.givemeamovie.model.network.movie_detail.Video
import com.web0zz.givemeamovie.model.network.movie_lists.Movie_list
import javax.inject.Inject

class DetailRepository @Inject constructor(
    private val movieDetailService: MovieDetailService,
    private val movieLibraryWithMoviesDao: MovieLibraryWithMoviesDao,
    private val movieDao: MovieDao
) : Repository {

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

    suspend fun fetchMovieDetail(movie_id: Int): Resource<Detail> {
        return Resource.toResource { movieDetailService.fetchMovieDetail(movie_id) }
    }

    suspend fun fetchMovieCast(movie_id: Int): Resource<Cast_and_Crew> {
        return Resource.toResource { movieDetailService.fetchMovieCast(movie_id) }
    }

    suspend fun fetchVideos(movie_id: Int): Resource<Video> {
        return Resource.toResource { movieDetailService.fetchVideos(movie_id) }
    }

    suspend fun fetchSimilarMovies(movie_id: Int): Resource<Movie_list> {
        return Resource.toResource { movieDetailService.fetchSimilarMovie(movie_id, 1) }
    }
}
