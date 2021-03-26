package com.web0zz.givemeamovie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.web0zz.givemeamovie.databinding.MovieViewBinding
import com.web0zz.givemeamovie.model.entity.Movie

class MovieListAdapter(
        private val items: List<Movie>
) : RecyclerView.Adapter<MovieListAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieViewBinding.inflate(layoutInflater)

        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        with(holder.binding) {
            movie = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size

    class MoviesViewHolder(val binding: MovieViewBinding) :
            RecyclerView.ViewHolder(binding.root)
}
