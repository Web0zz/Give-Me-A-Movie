package com.web0zz.givemeamovie.view.ui.detail

import androidx.lifecycle.*
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.MovieLibrary
import com.web0zz.givemeamovie.repository.MovieWatchListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val movieWatchListRepository: MovieWatchListRepository
) : ViewModel() {

    private val _selectedMovie = MutableLiveData<Movie>()

    private var _availableLists: MutableLiveData<List<MovieLibrary>> = MutableLiveData()
    val availableLists: LiveData<List<MovieLibrary>> = _availableLists

    init {
        getAvailableLibraries()
    }

    fun setSelectedMovie(movie: Movie) {
        _selectedMovie.postValue(movie)
    }

    private fun getAvailableLibraries() {
        viewModelScope.launch(Dispatchers.IO) {
            _availableLists.postValue(movieWatchListRepository.getAvailableLibraries())
        }
    }

    fun insertMovieToNewLibrary(library_name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieWatchListRepository.insertNewLibrary(library_name, _selectedMovie.value!!)
            movieWatchListRepository.insertMovie(_selectedMovie.value!!)
            movieWatchListRepository.insertMovieToLibrary(_selectedMovie.value!!, library_name)
        }
    }

    fun insertMovieToAvailableLibrary(library_name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieWatchListRepository.insertMovieToLibrary(_selectedMovie.value!!, library_name)
            movieWatchListRepository.insertMovie(_selectedMovie.value!!)
        }
    }

    fun deleteMovieFromAvailableLibrary(library_name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieWatchListRepository.deleteMovieFromLibrary(_selectedMovie.value!!, library_name)
            movieWatchListRepository.deleteMovie(_selectedMovie.value!!)
        }
    }
}
