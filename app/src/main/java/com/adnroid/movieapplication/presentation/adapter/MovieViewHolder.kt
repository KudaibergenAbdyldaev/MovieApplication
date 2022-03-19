package com.adnroid.movieapplication.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.adnroid.movieapplication.databinding.PopularMovieItemBinding
import com.adnroid.movieapplication.presentation.extension.loadImageFromUrl
import com.pacckage.domain.Results

class MovieViewHolder(private val binding: PopularMovieItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Results?) {
        binding.image.loadImageFromUrl(item?.img?:"")
    }
}