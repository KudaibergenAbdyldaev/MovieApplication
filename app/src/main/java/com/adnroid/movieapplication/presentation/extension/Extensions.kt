package com.adnroid.movieapplication.presentation.extension

import android.widget.ImageView
import com.adnroid.movieapplication.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

fun ImageView.loadImageFromUrl(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(this)
}