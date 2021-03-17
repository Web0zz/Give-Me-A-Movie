package com.web0zz.givemeamovie.view.ui.home

import androidx.lifecycle.*
import com.web0zz.givemeamovie.data.remote.Resource
import com.web0zz.givemeamovie.model.network.movie_lists.Movie_list
import com.web0zz.givemeamovie.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val homeRepository: HomeRepository
): ViewModel() {

    private lateinit var _searchMovieList: LiveData<Resource<Movie_list>>
    val searchMovieList: LiveData<Resource<Movie_list>> = _searchMovieList

    lateinit var nowPlayingMovie:LiveData<Resource<Movie_list>>
    lateinit var popularMovie:LiveData<Resource<Movie_list>>
    lateinit var topRatedMovie:LiveData<Resource<Movie_list>>


    fun searchMovie(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _searchMovieList = homeRepository.fetchSearchMovie(query).asLiveData()
        }
    }

    fun fetchNowPlayingMovie(page: Int = 1) {
        viewModelScope.launch(Dispatchers.IO) {
            nowPlayingMovie = homeRepository.fetchNowPlayingMovie(page).asLiveData()
        }
    }

    fun fetchPopularMovie(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            popularMovie = homeRepository.fetchPopularMovies(page).asLiveData()
        }
    }

    fun fetchTopRatedMovie(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
           topRatedMovie = homeRepository.fetchTopRatedMovies(page).asLiveData()
        }
    }

}