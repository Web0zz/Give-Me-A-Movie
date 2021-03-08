package com.example.givemeamovie.view.ui.watchlist

import androidx.lifecycle.*
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary
import com.example.givemeamovie.repository.MovieWatchListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
        private val movieWatchListRepository: MovieWatchListRepository
): ViewModel() {

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> = _selectedMovie

    private lateinit var _availableLists: LiveData<List<MovieLibrary>>
    val availableLists: LiveData<List<MovieLibrary>> = _availableLists

    fun setSelectedMovie(movie: Movie) {
        _selectedMovie.postValue(movie)
    }

    fun getAvailableLibraries(){
        viewModelScope.launch(Dispatchers.IO) {
            _availableLists = movieWatchListRepository.getAvailableLibraries().asLiveData()
        }
    }

    fun insetMovieToNewLibrary(library_name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieWatchListRepository.insertNewLibrary(library_name,_selectedMovie.value!!)
            movieWatchListRepository.insertMovie(_selectedMovie.value!!)
            movieWatchListRepository.insertMovieToLibrary(_selectedMovie.value!!, library_name)
        }
    }

    fun insertMovieToAvailableLibrary(library_name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieWatchListRepository.insertMovie(_selectedMovie.value!!)
            movieWatchListRepository.insertMovieToLibrary(_selectedMovie.value!!, library_name)
        }
    }

    fun deleteMovieFromAvailableLibrary(library_name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieWatchListRepository.deleteMovieFromLibrary(_selectedMovie.value!!, library_name)
        }
    }

    fun deleteMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            movieWatchListRepository.deleteMovie(_selectedMovie.value!!)
        }
    }
}