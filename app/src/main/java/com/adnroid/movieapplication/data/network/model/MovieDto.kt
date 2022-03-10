package com.adnroid.movieapplication.data.network.model


data class MovieDto(
    val page: Int,
    val results: List<ResultsDto>
)