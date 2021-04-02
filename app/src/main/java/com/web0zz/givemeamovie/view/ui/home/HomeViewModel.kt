package com.web0zz.givemeamovie.view.ui.home

import androidx.lifecycle.*
import com.web0zz.givemeamovie.data.remote.Resource
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.network.movie_lists.MovieListSection
import com.web0zz.givemeamovie.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private var _searchMovieList: MutableLiveData<List<Movie>> = MutableLiveData()
    val searchMovieList: LiveData<List<Movie>> = _searchMovieList

    private var _listMovieSection: MutableLiveData<MutableList<MovieListSection>> = MutableLiveData(mutableListOf())
    val listMovieSection: LiveData<MutableList<MovieListSection>> = _listMovieSection

    fun searchMovie(
        query: String,
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val data = homeRepository.fetchSearchMovie(query)) {
                is Resource.Success -> _searchMovieList.postValue(data.data!!.results)
                is Resource.Error -> onError(data.message)
            }
        }
    }

    fun mainFunction(
        page: Int = 1,
        onError: (String?) -> Unit
    ) {
        if (_listMovieSection.value.isNullOrEmpty()) {
            fetchPopularMovie(page, onError)
            fetchTopRatedMovie(page, onError)
            fetchNowPlayingMovie(page, onError)
        }
    }

    private fun fetchNowPlayingMovie(
        page: Int = 1,
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val data = homeRepository.fetchNowPlayingMovie(page)) {
                is Resource.Success -> _listMovieSection.value!!.add(MovieListSection("Now Playing", data.data!!.results))
                is Resource.Error -> onError(data.message)
            }
        }
    }

    private fun fetchPopularMovie(
        page: Int = 1,
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val data = homeRepository.fetchPopularMovies(page)) {
                is Resource.Success -> _listMovieSection.value!!.add(MovieListSection("Popular", data.data!!.results))
                is Resource.Error -> onError(data.message)
            }
        }
    }

    private fun fetchTopRatedMovie(
        page: Int = 1,
        onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val data = homeRepository.fetchTopRatedMovies(page)) {
                is Resource.Success -> _listMovieSection.value!!.add(MovieListSection("Top Rated", data.data!!.results))
                is Resource.Error -> onError(data.message)
            }
        }
    }
}
