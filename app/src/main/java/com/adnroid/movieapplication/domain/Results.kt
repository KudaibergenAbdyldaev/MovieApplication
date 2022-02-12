package com.adnroid.movieapplication.domain

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("poster_path")val img: String? = null,
    val id: Int? = null,
    val title: String? = null,
    val overview: String
)