package com.example.givemeamovie.view.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.givemeamovie.data.remote.Resource
import com.example.givemeamovie.model.network.movie_lists.Movie_list
import com.example.givemeamovie.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val homeRepository: HomeRepository
): ViewModel() {

    private val _searchMovieList = MutableLiveData<Resource<Movie_list>>()
    val searchMovieList: LiveData<Resource<Movie_list>> = _searchMovieList

    private val _movieList = MutableLiveData<List<Resource<Movie_list>>>()
    val movieList: LiveData<List<Resource<Movie_list>>> = _movieList

    fun searchMovie(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchMovieList.postValue(homeRepository.fetchSearchMovie(query))
        }
    }

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _movieList.postValue(
                    listOf(
                            homeRepository.fetchNowPlayingMovie(),
                            homeRepository.fetchPopularMovies(),
                            homeRepository.fetchTopRatedMovies()
                    )
            )
        }
    }
}