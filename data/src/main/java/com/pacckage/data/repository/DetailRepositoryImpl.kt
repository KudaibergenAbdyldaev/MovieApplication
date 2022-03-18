package com.pacckage.data.repository

import com.pacckage.data.mapper.MovieMapper
import com.pacckage.data.network.ApiInterface
import com.pacckage.domain.DetailMovie
import com.pacckage.domain.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val mapper: MovieMapper
) : DetailRepository {

    override suspend fun getDetailMovie(id: Int): DetailMovie {
        return mapper.mapDetailDtoToDetailMovie(
            apiInterface.getDetailMovie(
                id,
                "bd8c2d1b4af41b4060929a043be50eb6",
                "ru-RU"
            )
        )
    }

}