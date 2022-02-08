package com.adnroid.movieapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: ResultsDto
)