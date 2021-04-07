package com.web0zz.givemeamovie.view.ui.favorite

import androidx.lifecycle.*
import com.web0zz.givemeamovie.model.entity.LibrarywithMovies
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.entity.MovieLibrary
import com.web0zz.givemeamovie.repository.FavoriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private var _moviesAndLibraries: MutableLiveData<LibrarywithMovies> = MutableLiveData()
    val moviesAndLibraries: LiveData<LibrarywithMovies> = _moviesAndLibraries

    private var _movieLibraries: List<MovieLibrary> = mutableListOf()

    private var _movieList: MutableList<List<Movie>> = mutableListOf()

    init {
        viewModelScope.launch {
            _movieLibraries = favoriteRepository.getAvailableLibraries()
        }
        _movieLibraries.map {
            viewModelScope.launch {
                _movieList.add(favoriteRepository.getMoviesInLibrary(it.library_Name))
            }
        }
        _moviesAndLibraries.postValue(LibrarywithMovies(_movieLibraries, _movieList))
    }
}
