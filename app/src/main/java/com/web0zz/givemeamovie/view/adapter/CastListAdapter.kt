package com.web0zz.givemeamovie.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.web0zz.givemeamovie.databinding.CastViewBinding
import com.web0zz.givemeamovie.model.network.credits.Cast

class CastListAdapter(
    private val items: List<Cast>
) : RecyclerView.Adapter<CastListAdapter.CastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CastViewBinding.inflate(layoutInflater)

        return CastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        with(holder.binding) {
            cast = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int = items.size

    class CastViewHolder(val binding: CastViewBinding) :
        RecyclerView.ViewHolder(binding.root)
}
