package com.example.givemeamovie.view.ui.liked

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.entity.MovieLibrary
import com.example.givemeamovie.repository.LikedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LikedViewModel @Inject constructor(
    private val likedRepository: LikedRepository
): ViewModel() {

    private val _movieLibraries = MutableLiveData<List<MovieLibrary>>()
    val movieLibraries: LiveData<List<MovieLibrary>> = _movieLibraries

    private val _movieLists = MutableLiveData<List<List<Movie>>>()
    val movieLists: LiveData<List<List<Movie>>> = _movieLists

}