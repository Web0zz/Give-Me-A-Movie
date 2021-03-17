package com.web0zz.givemeamovie.view.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.web0zz.givemeamovie.data.remote.Resource
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.RecommendMovie
import com.web0zz.givemeamovie.model.network.movie_lists.Movie_list
import com.web0zz.givemeamovie.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
        private val exploreRepository: ExploreRepository
): ViewModel() {

    private val _recommendedMovie = MutableLiveData<Movie_list>()
    val recommendedMovie: LiveData<Movie_list> = _recommendedMovie

    private var _likedMovies: MutableList<Movie> = mutableListOf()
    private var _fetchedMovies: Movie_list = Movie_list(-1, emptyList(),0,0)

    // When movie liked or watched it will added to library
    fun addMovieYourLibrary(movie: Movie, library_name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            exploreRepository.addMovieToDatabase(movie, library_name)
        }
    }


    /// Main functions will called from UI
    fun fetchNewMovieToRecommendService(
            onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchMoviesFromYourLikes(onError)
            putMovieToRecommendetion()
        }
    }

    fun RecommendedMovieListToView(
            onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieToFetch = exploreRepository.pullMovieToRecommendation()
            fetchRecommendedMovies(movieToFetch.movieId, 1, onError)
        }
    }
    /// ***

    private suspend fun fetchRecommendedMovies(
            movie_id: Int,
            page: Int = 1,
            onError: (String?) -> Unit
    ) {
        when(val list = exploreRepository.getRecommendedMovies(movie_id, page)) {
            is Resource.Success -> {
                _recommendedMovie.postValue(list.data!!)
            }
            is Resource.Error -> {
                onError(list.message)
            }
        }
    }

    private suspend fun fetchMoviesFromYourLikes(
            onError: (String?) -> Unit
    ) {
        exploreRepository.getMovies(
                onLikedMovieList = {
                    _likedMovies = it
                },
                onServiceCalled = {
                    when(it) {
                        is Resource.Success -> _fetchedMovies = it.data!!
                        is Resource.Error -> onError(it.message)
                    }
                },
                onMainError = {
                    onError(it)
                }
        )

    }

    private suspend fun putMovieToRecommendetion() {
        if (_likedMovies != emptyList<Movie>()) {
            _likedMovies.map {
                exploreRepository.addMovieToRecommendation(RecommendMovie(it.movie_id))
            }
            _likedMovies.clear()
        }
        if(_fetchedMovies.page != -1) {
            _fetchedMovies.results.map {
                exploreRepository.addMovieToRecommendation(RecommendMovie(it.movie_id))
            }
            _fetchedMovies = Movie_list(-1, emptyList(),0,0)
        }
    }
}