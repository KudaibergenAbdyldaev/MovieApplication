package com.adnroid.movieapplication.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adnroid.movieapplication.R
import com.adnroid.movieapplication.databinding.LoadItemBinding

class LoaderStateAdapter :
    LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {

    override fun onBindViewHolder(holder: LoaderViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoaderViewHolder {
        val binding: LoadItemBinding = LoadItemBinding.bind(
            LayoutInflater.from(parent.context).inflate(
                R.layout.load_item, parent, false
            )
        )
        return LoaderViewHolder(binding)
    }

    class LoaderViewHolder(private val binding: LoadItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.progress.isVisible = loadState is LoadState.Loading
        }
    }
}