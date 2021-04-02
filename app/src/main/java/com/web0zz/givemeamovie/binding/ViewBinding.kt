package com.web0zz.givemeamovie.binding

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.web0zz.givemeamovie.model.network.movie_detail.Detail

object ViewBinding {

    @JvmStatic
    @BindingAdapter("LoadImage")
    fun bindLoadImage(view: AppCompatImageView, movie_image_url: String) {
        Glide.with(view.context)
            .load(movie_image_url)
            .into(view)
    }

    @JvmStatic
    @BindingAdapter("LoadCircleImage")
    fun bindLoadCircleImage(view: AppCompatImageView, movie_cast_url: String) {
        Glide.with(view.context)
            .load(movie_cast_url)
            .apply(RequestOptions().circleCrop())
            .into(view)
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("MovieTitle")
    fun bindingMovieTitle(view: TextView, movie_title: Detail?) {
        if (movie_title != null) {
            view.text = "${movie_title.original_title} (${movie_title.release_date.subSequence(0,4)})"
        } else {
            view.text = "Loading..."
        }
    }

    @JvmStatic
    @SuppressLint("SetTextI18n")
    @BindingAdapter("MovieRuntime")
    fun bindRunTime(view: TextView, movie_runtime: Detail?) {
        if (movie_runtime != null) {
            view.text = "${movie_runtime.runtime} min"
        } else {
            view.text = "not found ?"
        }
    }

    @SuppressLint("SetTextI18n")
    @JvmStatic
    @BindingAdapter("Genres")
    fun bindGenres(view: TextView, movie_genre: Detail?) {
        if (movie_genre != null) {
            val genres = mutableListOf<String>()
            movie_genre.genres.map {
                genres.add(it.name)
            }
            view.text = genres.joinToString(",")
        } else {
            view.text = "not found info?"
        }
    }

    @JvmStatic
    @SuppressLint("SetTextI18n")
    @BindingAdapter("WatchlistSize")
    fun bindWatchListSize(view: TextView, size: Int) {
        view.text = "$size - on the list"
    }
}
