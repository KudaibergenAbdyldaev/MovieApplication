package com.adnroid.movieapplication.data.network.model

import com.google.gson.annotations.SerializedName

data class ResultsDto(
    @SerializedName("poster_path") val img: String? = null,
    @SerializedName("id") val id: Int? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("overview") val overview: String
)