package com.example.givemeamovie.binding

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.givemeamovie.model.entity.Movie
import com.example.givemeamovie.model.network.movie_detail.Detail

object ViewBinding {

    @JvmStatic
    @BindingAdapter("LoadImage")
    fun bindLoadImage(view: AppCompatImageView, url: String) {
        Glide.with(view.context)
                .load(url)
                .into(view)
    }

    @JvmStatic
    @BindingAdapter("Overview")
    fun bindOverview(view: TextView, movie: Detail) {
        view.text = movie.overview
    }

    @JvmStatic
    @BindingAdapter("ReleaseDate")
    fun bindReleaseDate(view: TextView, movie: Detail) {
        view.text = movie.release_date.subSequence(0,4)
    }

    @JvmStatic
    @SuppressLint("SetTextI18n")
    @BindingAdapter("MovieRuntime")
    fun bindRunTime(view: TextView, movie: Detail) {
        view.text = "${movie.runtime} min"
    }

    @JvmStatic
    @BindingAdapter("Genres")
    fun bindGenres(view: TextView, movie: Detail) {
        val genres = mutableListOf<String>()
        movie.genres.map {
            genres.add(it.name)
        }
        view.text = genres.joinToString(",")
    }

}