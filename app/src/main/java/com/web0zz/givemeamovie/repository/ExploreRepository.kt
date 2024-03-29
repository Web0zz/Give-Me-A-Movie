package com.web0zz.givemeamovie.repository

import com.web0zz.givemeamovie.data.local.LikedMovieRecommendationDao
import com.web0zz.givemeamovie.data.local.MovieDao
import com.web0zz.givemeamovie.data.local.MovieLibraryWithMoviesDao
import com.web0zz.givemeamovie.data.remote.Resource
import com.web0zz.givemeamovie.data.remote.service.MovieListService
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.MovieLibraryCrossRef
import com.web0zz.givemeamovie.model.entity.RecommendMovie
import com.web0zz.givemeamovie.model.network.movie_lists.Movie_list
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ExploreRepository @Inject constructor(
    private val movieListService: MovieListService,
    private val movieDao: MovieDao,
    private val croffRefDao: MovieLibraryWithMoviesDao,
    private val likedMovieRecommendationDao: LikedMovieRecommendationDao
) : Repository {

    companion object {
        var currentPage = 1
    }

    private var temporaryList = mutableListOf<Resource<Movie_list>>()

    suspend fun addMovieToRecommendation(recommendMovie: RecommendMovie) {
        likedMovieRecommendationDao.addMovieForRecommendation(recommendMovie)
    }

    suspend fun pullMovieToRecommendation(): RecommendMovie {
        val movie = likedMovieRecommendationDao.takeMovieToRecommend()
        likedMovieRecommendationDao.deleteMovie(movie)
        return movie
    }

    // Get Movies in Liked database
    private suspend fun moviesInLikedCross(
        onSuccess: suspend (List<MovieLibraryCrossRef>) -> Unit,
        onError: () -> Unit
    ) = withContext(Dispatchers.IO) {
        try {
            val movieList = croffRefDao.getMovies("LIKED")
            if (movieList != emptyList<MovieLibraryCrossRef>()) {
                onSuccess(movieList)
            } else {
                onError()
            }
        } catch (e: Throwable) {
            onError()
        }
    }

   /* Get popular movie from api when there is no liked movie
   *  also when get liked movie operation had failed
   *  */
    private suspend fun getMovieFromPopular(
        onSuccess: suspend (Resource<Movie_list>) -> Unit,
        onError: (Throwable) -> Unit
    ) = withContext(Dispatchers.IO) {
        try {
            if (temporaryList != emptyList<Resource<Movie_list>>()) {
                temporaryList.clear()
                currentPage++
            }
            val movieList = Resource.toResource { movieListService.fetchPopularMovie(currentPage) }
            onSuccess(movieList)
        } catch (ex: Throwable) {
            onError(ex)
        }
    }

    /*  It will send Movie list when there is liked movie
    *   if can't find movie in liked
    *   will take data from API service
    *   if everything is go wrong, it will execute error function
    * */
    suspend fun getMovies(
        onLikedMovieList: (MutableList<Movie>) -> Unit,
        onServiceCalled: (Resource<Movie_list>) -> Unit,
        onMainError: (String?) -> Unit
    ) = withContext(Dispatchers.IO) {
        val movieLikedList: MutableList<Movie> = mutableListOf()
        try {
            moviesInLikedCross(
                onSuccess = {
                    try {
                        it.map { movieLibraryCrossRef ->
                            val getMovie = movieDao.getMovie(movieLibraryCrossRef.movie_id)
                            if (getMovie != null) movieLikedList.add(getMovie)
                        }
                        onLikedMovieList(movieLikedList)
                    } catch (ex: Throwable) {
                        onMainError(ex.localizedMessage)
                    }
                },
                onError = {
                    launch(Dispatchers.IO) {
                        getMovieFromPopular(
                            onSuccess = {
                                onServiceCalled(it)
                            },
                            onError = {
                                onMainError(it.localizedMessage)
                            }
                        )
                    }
                }
            )
        } catch (ex: Throwable) {
            onMainError(ex.localizedMessage)
        }
    }

    suspend fun getRecommendedMovies(movie_id: Int, page: Int = 1): Resource<Movie_list> {
        return Resource.toResource { movieListService.fetchRecommendationMovie(movie_id, page) }
    }

    suspend fun addMovieToDatabase(movie: Movie, library_name: String) {
        movieDao.insertMovieList(listOf(movie))
        croffRefDao.insert(MovieLibraryCrossRef(library_name, movie.movie_id))
    }
}
