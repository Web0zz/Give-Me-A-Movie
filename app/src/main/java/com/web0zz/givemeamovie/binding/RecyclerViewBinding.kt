package com.web0zz.givemeamovie.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.web0zz.givemeamovie.model.entity.Movie
import com.web0zz.givemeamovie.model.network.credits.Cast
import com.web0zz.givemeamovie.view.adapter.CastListAdapter
import com.web0zz.givemeamovie.view.adapter.MovieListAdapter

object RecyclerViewBinding {

    @JvmStatic
    @BindingAdapter("adapterCastList")
    fun bindAdapterCastList(view: RecyclerView, cast: List<Cast>) {
        view.adapter = CastListAdapter(cast)
    }

    @JvmStatic
    @BindingAdapter("adapterMovieList")
    fun bindAdapterMovieList(view: RecyclerView, movie: List<Movie>) {
        view.adapter = MovieListAdapter(movie)
    }
}