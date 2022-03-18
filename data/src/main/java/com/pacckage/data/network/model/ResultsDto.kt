package com.pacckage.data.network.model

import com.google.gson.annotations.SerializedName

data class ResultsDto(
    @SerializedName("poster_path")val img: String? = null,
    val id: Int? = null,
    val title: String? = null,
    val overview: String
)