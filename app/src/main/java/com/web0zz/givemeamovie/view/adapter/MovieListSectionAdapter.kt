package com.web0zz.givemeamovie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.web0zz.givemeamovie.databinding.MovieListSectionViewBinding
import com.web0zz.givemeamovie.model.network.movie_lists.MovieListSection

class MovieListSectionAdapter(
    private val items: List<MovieListSection>,
    private val onClickListener: MovieListAdapter.MovieClickListener
) : RecyclerView.Adapter<MovieListSectionAdapter.MovieSectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieListSectionViewBinding.inflate(layoutInflater)

        return MovieSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieSectionViewHolder, position: Int) {
        with(holder.binding) {
            movieSection = items[position]
            clickListener = onClickListener
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size
    class MovieSectionViewHolder(val binding: MovieListSectionViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
