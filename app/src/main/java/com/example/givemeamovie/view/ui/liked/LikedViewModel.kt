package com.example.givemeamovie.view.ui.liked

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary
import com.example.givemeamovie.repository.LikedRepository
import com.example.givemeamovie.repository.MovieWatchListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LikedViewModel @Inject constructor(
    private val likedRepository: LikedRepository,
    private val movieWatchListRepository: MovieWatchListRepository
): ViewModel() {

    private val _movieLibraries = MutableLiveData<List<MovieLibrary>>()
    val movieLibraries: LiveData<List<MovieLibrary>> = _movieLibraries

    private val _movieLists = MutableLiveData<MutableList<List<Movie>>>()
    val movieLists: LiveData<MutableList<List<Movie>>> = _movieLists


    fun getAvailableLibraries() {
        _movieLibraries.postValue(likedRepository.getAvailableLibraries())
    }

    fun getMoviesInLibrary(library_name: String) {
        val libraries = movieWatchListRepository.getAvailabeleLibraries()
        libraries.map {
            _movieLists.value?.add(likedRepository.getMoviesInLibrary(it.library_Name))
        }
    }
}