package com.adnroid.movieapplication.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.pacckage.domain.Results

object DiffUtilCallBack : DiffUtil.ItemCallback<Results>() {
    override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
        return oldItem == newItem
    }
}