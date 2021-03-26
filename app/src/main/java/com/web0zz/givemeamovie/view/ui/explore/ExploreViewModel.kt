package com.web0zz.givemeamovie.view.ui.explore

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
): ViewModel() {

    var movieIndex: Int = 0
    lateinit var currentMovie: MutableLiveData<Movie>
    lateinit var currentMovieList: MutableLiveData<List<Movie>>

    private var _likedMovies: MutableList<Movie> = mutableListOf()
    private var _fetchedMovies: Movie_list = Movie_list(-1, emptyList(),0,0)


    init {
        fetchNewMovieToRecommendService {
            Timber.d("error in fetch movie to recommend database error: $it")
        }
        recommendedMovieListToView {
            Timber.d("error in fetch movie to recommend variable error: $it")
        }
        currentMovie.postValue( currentMovieList.value!![movieIndex] )
    }

    fun getNewMovieForUi(isLiked: Boolean) {
        if (isLiked) { addMovieYourLibrary(currentMovie.value!!) }
        if (movieIndex == currentMovieList.value!!.size) {
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
        currentMovie.postValue(currentMovieList.value!![movieIndex])
    }



    // When movie liked it will added to library
    private fun addMovieYourLibrary(movie: Movie) {
        viewModelScope.launch(Dispatchers.IO) {
            exploreRepository.addMovieToDatabase(movie, "LIKED")
        }
    }


    /// Main functions
    private fun fetchNewMovieToRecommendService(
            onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            fetchMoviesFromYourLikes(onError)
            putMovieToRecommendetion()
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
    /// ***

    private suspend fun fetchRecommendedMovies(
            movie_id: Int,
            page: Int = 1,
            onError: (String?) -> Unit
    ) {
        when(val list = exploreRepository.getRecommendedMovies(movie_id, page)) {
            is Resource.Success -> {
                currentMovieList.postValue(list.data!!.results)
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