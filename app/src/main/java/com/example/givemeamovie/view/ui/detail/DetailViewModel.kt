package com.example.givemeamovie.view.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.givemeamovie.model.network.credits.Cast_and_Crew
import com.example.givemeamovie.model.network.keywords.Keyword_List
import com.example.givemeamovie.model.network.movie_detail.Detail
import com.example.givemeamovie.model.network.movie_detail.Video
import com.example.givemeamovie.repository.DetailRepository
import com.example.givemeamovie.repository.LikedRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
        private val detailRepository: DetailRepository,
        private val likedRepository: LikedRepository
): ViewModel() {

    private val _movieDetail = MutableLiveData<Detail>()
    val movieDetail: LiveData<Detail> = _movieDetail

    private val _movieCast = MutableLiveData<Cast_and_Crew>()
    val movieCast: LiveData<Cast_and_Crew> = _movieCast

    private val _movieVideo = MutableLiveData<Video>()
    val movieVideo: LiveData<Video> = _movieVideo

    private val _movieKeywords = MutableLiveData<Keyword_List>()
    val moveieKeywords: LiveData<Keyword_List> = _movieKeywords



}