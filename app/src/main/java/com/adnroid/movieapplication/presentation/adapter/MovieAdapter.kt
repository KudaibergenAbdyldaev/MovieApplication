package com.adnroid.movieapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.adnroid.movieapplication.R
import com.adnroid.movieapplication.databinding.PopularMovieItemBinding
import com.adnroid.movieapplication.domain.Results
import com.bumptech.glide.Glide

class MovieAdapter : PagingDataAdapter<Results, MovieAdapter.ViewHolder>(DiffUtilCallBack) {

    inner class ViewHolder(private val binding: PopularMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Results?) {
            Glide
                .with(binding.root.context)
                .load("https://image.tmdb.org/t/p/w500/${item?.img}")
                .into(binding.image)
        }
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: PopularMovieItemBinding = PopularMovieItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(
                R.layout.popular_movie_item, parent, false
            )
        )
        return ViewHolder(binding)
    }


    object DiffUtilCallBack : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem == newItem
        }
    }

}