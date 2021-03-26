package com.web0zz.givemeamovie.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.web0zz.givemeamovie.databinding.MovieListSectionViewBinding
import com.web0zz.givemeamovie.model.network.movie_lists.MovieListSection

class MovieListSectionAdapter(
        private val items: List<MovieListSection>
): RecyclerView.Adapter<MovieListSectionAdapter.MovieSectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSectionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MovieListSectionViewBinding.inflate(layoutInflater)

        return MovieSectionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieSectionViewHolder, position: Int) {
        with(holder.binding) {
            movieSection = items[position]
            executePendingBindings()
        }
        // !!!
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(holder.binding.root.context, LinearLayoutManager.HORIZONTAL, false)
        holder.binding.moviesRecyclerview.layoutManager = layoutManager
        holder.binding.moviesRecyclerview.setHasFixedSize(true)
    }

    override fun getItemCount() = items.size
    class MovieSectionViewHolder(val binding: MovieListSectionViewBinding) :
            RecyclerView.ViewHolder(binding.root)
}