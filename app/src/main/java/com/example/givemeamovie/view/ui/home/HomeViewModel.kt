package com.example.givemeamovie.view.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
        private val homeRepository: HomeRepository
): ViewModel() {

    private val _searchMovieList = MutableLiveData<List<Movie>>()
    val searchMovieList: LiveData<List<Movie>> = _searchMovieList

    private val _movieList = MutableLiveData<List<List<Movie>>>()
    val movieList: LiveData<List<List<Movie>>> = _movieList
}