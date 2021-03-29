package com.web0zz.givemeamovie.view.ui.home

import androidx.lifecycle.*
import com.web0zz.givemeamovie.data.remote.Resource
import com.web0zz.givemeamovie.model.entity.Movie
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

    lateinit var searchMovieList: MutableLiveData<List<Movie>>

    lateinit var nowPlayingMovie:MutableLiveData<Movie_list>
    lateinit var popularMovie:MutableLiveData<Movie_list>
    lateinit var topRatedMovie:MutableLiveData<Movie_list>

    fun searchMovie(
            query: String,
            onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = homeRepository.fetchSearchMovie(query).asLiveData()
            when(data.value) {
                is Resource.Success -> {
                    searchMovieList.postValue(data.value!!.data!!.results)
                }
                is Resource.Error -> {
                    onError(data.value!!.message)
                }
            }
        }
    }

    fun mainFunction(
            page: Int = 1,
            onError: (String?) -> Unit
    ) {
        fetchNowPlayingMovie(page, onError)
        fetchPopularMovie(page, onError)
        fetchTopRatedMovie(page, onError)
    }

    private fun fetchNowPlayingMovie(
            page: Int = 1,
            onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = homeRepository.fetchNowPlayingMovie(page).asLiveData()
            when(data.value) {
                is Resource.Success -> {
                    nowPlayingMovie.postValue(data.value!!.data)
                }
                is Resource.Error -> {
                    onError(data.value!!.message)
                }
            }
        }
    }

    private fun fetchPopularMovie(
            page: Int = 1,
            onError: (String?) -> Unit
    ) {
        val data = homeRepository.fetchPopularMovies(page).asLiveData()
        viewModelScope.launch(Dispatchers.IO) {
            when(data.value) {
                is Resource.Success -> {
                    popularMovie.postValue(data.value!!.data)
                }
                is Resource.Error -> {
                    onError(data.value!!.message)
                }
            }
        }
    }

    private fun fetchTopRatedMovie(
            page: Int = 1,
            onError: (String?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = homeRepository.fetchTopRatedMovies(page).asLiveData()
            when(data.value) {
                is Resource.Success -> {
                    topRatedMovie.postValue(data.value!!.data)
                }
                is Resource.Error -> {
                    onError(data.value!!.message)
                }
            }
        }
    }

}