package com.example.givemeamovie.view.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
        private val exploreRepository: ExploreRepository
): ViewModel() {

    private val _recommendedMovie = MutableLiveData<List<Movie>>()
    val recommendedMovie: LiveData<List<Movie>> = _recommendedMovie
}