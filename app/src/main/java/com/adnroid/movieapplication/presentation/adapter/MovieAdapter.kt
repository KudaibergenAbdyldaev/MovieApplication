package com.adnroid.movieapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.adnroid.movieapplication.R
import com.adnroid.movieapplication.databinding.PopularMovieItemBinding
import com.pacckage.domain.Results

class MovieAdapter : PagingDataAdapter<Results, MovieViewHolder>(DiffUtilCallBack) {

    var onMovieItemClickListener: ((Int) -> Unit)? = null

    override fun onBindViewHolder(holderMovie: MovieViewHolder, position: Int) {
        holderMovie.bind(getItem(position))
        holderMovie.itemView.setOnClickListener {
            onMovieItemClickListener?.invoke(getItem(position)?.id ?: 0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding: PopularMovieItemBinding = PopularMovieItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(
                R.layout.popular_movie_item, parent, false
            )
        )
        return MovieViewHolder(binding)
    }

}