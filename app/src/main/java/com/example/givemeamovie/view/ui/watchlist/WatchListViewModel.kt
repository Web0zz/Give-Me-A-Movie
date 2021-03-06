package com.example.givemeamovie.view.ui.watchlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary
import com.example.givemeamovie.repository.MovieWatchListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
        private val movieWatchListRepository: MovieWatchListRepository
): ViewModel() {

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie> = _selectedMovie

    private val _availableLists = MutableLiveData<List<MovieLibrary>>()
    val availableLists: LiveData<List<MovieLibrary>> = _availableLists

    fun setSelectedMovie(movie: Movie) {
        _selectedMovie.postValue(movie)
    }

    fun getAvailableLibraries(){
        _availableLists.postValue(movieWatchListRepository.getAvailabeleLibraries())
    }

    fun insetMovieToNewLibrary(library_name: String) {
        movieWatchListRepository.insertNewLibrary(library_name)
        movieWatchListRepository.insertMovie(_selectedMovie.value!!)
        movieWatchListRepository.insertMovieToLibrary(_selectedMovie.value!!, library_name)
    }

    fun insertMovieToAvailableLibrary(library_name: String) {
        movieWatchListRepository.insertMovie(_selectedMovie.value!!)
        movieWatchListRepository.insertMovieToLibrary(_selectedMovie.value!!, library_name)
    }

    fun deleteMovieFromAvailableLibrary(library_name: String) {
        movieWatchListRepository.deleteMovieFromLibrary(_selectedMovie.value!!, library_name)
    }

    fun deleteMovie() {
        movieWatchListRepository.deleteMovie(_selectedMovie.value!!)
    }
}