package com.example.givemeamovie.view.ui.explore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.givemeamovie.data.remote.Resource
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.network.movie_lists.Movie_list
import com.example.givemeamovie.repository.ExploreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
        private val exploreRepository: ExploreRepository
): ViewModel() {

    private val _recommendedMovie = MutableLiveData<Resource<Movie_list>>()
    val recommendedMovie: LiveData<Resource<Movie_list>> = _recommendedMovie

    private val _likedMovies = MutableLiveData<List<Movie>>()
    val likedMovies: LiveData<List<Movie>> = _likedMovies


}