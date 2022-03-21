package com.pacckage.data.network.model

import com.google.gson.annotations.SerializedName

data class DetailMovieDto(
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String
)