package com.adnroid.movieapplication.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.adnroid.movieapplication.databinding.PopularMovieItemBinding
import com.bumptech.glide.Glide
import com.pacckage.domain.Results

class MovieViewHolder(private val binding: PopularMovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Results?) {
        Glide
            .with(binding.root.context)
            .load(item?.img?:"")
            .into(binding.image)

    }
}