package com.adnroid.movieapplication.data.repository

import com.adnroid.movieapplication.data.mapper.MovieMapper
import com.adnroid.movieapplication.data.network.ApiInterface
import com.adnroid.movieapplication.domain.Movie
import com.adnroid.movieapplication.domain.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val mapper: MovieMapper,
    private val apiInterface: ApiInterface
) : MovieRepository {


    override suspend fun getPopularMovie(page: Int): Movie {
        return mapper.mapDtoToModel(
            apiInterface.getPopularMovie(
                "bd8c2d1b4af41b4060929a043be50eb6",
                "",
                page
            )
        )
    }

}