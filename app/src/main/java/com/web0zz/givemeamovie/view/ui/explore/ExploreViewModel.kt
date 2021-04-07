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
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val exploreRepository: ExploreRepository
) : ViewModel() {

    private var movieIndex: Int = 0

    private var _currentMovie: MutableLiveData<Movie> = MutableLiveData()
    val currentMovie: LiveData<Movie> = _currentMovie

    private var _currentMovieList: MutableLiveData<List<Movie>> = MutableLiveData()
    val currentMovieList: LiveData<List<Movie>> = _currentMovieList

    private var _likedMovies: MutableList<Movie> = mutableListOf()
    private var _fetchedMovies: Movie_list = Movie_list(-1, emptyList(), 0, 0)

    init {
        fetchNewMovieToRecommendService {
            Timber.d("error in fetch movie to recommend database error: $it")
        }
        recommendedMovieListToView {
            Timber.d("error in fetch movie to recommend variable error: $it")
        }
    }

    fun firstInit() {
        if (_currentMovie.value == null && _currentMovieList.value != null) {
            _currentMovie.postValue(_currentMovieList.value!![0])
        }
    }

    fun getNewMovieForUi(isLiked: Boolean) {
        if (isLiked) { addMovieYourLibrary(_currentMovie.value!!) }
        if (movieIndex == _currentMovieList.value!!.size) {
            recommendedMovieListToView {
                fetchNewMovieToRecommendService {
                    Timber.d("error in fetch movie to recommend database error: $it")
                }
                recommendedMovieListToView {
                    Timber.d("error in fetch movie to recommend variable error: $it")
                }
            }
            movieIndex = 0
        } else {
            movieIndex++
        }
        _currentMovie.postValue(_currentMovieList.value!![movieIndex])
    }

    // When movie liked it will added to library
    private fun addMovieYourLibrary(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            exploreRepository.addMovieToDatabase(movie, "LIKED")
        }
    }

    // / Main functions
    private fun fetchNewMovieToRecommendService(
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchMoviesFromYourLikes(onError)
            putMovieToRecommendation()
        }
    }

    private fun recommendedMovieListToView(
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val movieToFetch = exploreRepository.pullMovieToRecommendation()
            fetchRecommendedMovies(movieToFetch.movieId, 1, onError)
        }
    }
    // / ***

    private suspend fun fetchRecommendedMovies(
        movie_id: Int,
        page: Int = 1,
        onError: (String?) -> Unit
    ) {
        when (val list = exploreRepository.getRecommendedMovies(movie_id, page)) {
            is Resource.Success -> {
                _currentMovieList.postValue(list.data!!.results)
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
                when (it) {
                    is Resource.Success -> _fetchedMovies = it.data!!
                    is Resource.Error -> onError(it.message)
                }
            },
            onMainError = {
                onError(it)
            }
        )
    }

    private suspend fun putMovieToRecommendation() {
        if (_likedMovies != emptyList<Movie>()) {
            _likedMovies.map {
                exploreRepository.addMovieToRecommendation(RecommendMovie(it.movie_id))
            }
            _likedMovies.clear()
        }
        if (_fetchedMovies.page != -1) {
            _fetchedMovies.results.map {
                exploreRepository.addMovieToRecommendation(RecommendMovie(it.movie_id))
            }
            _fetchedMovies = Movie_list(-1, emptyList(), 0, 0)
        }
    }
}
