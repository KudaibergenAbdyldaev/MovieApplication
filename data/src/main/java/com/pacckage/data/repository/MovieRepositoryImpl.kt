package com.pacckage.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.pacckage.data.mapper.MovieMapper
import com.pacckage.data.network.ApiInterface
import com.pacckage.domain.MovieRepository
import com.pacckage.domain.Results
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val mapper: MovieMapper
) : MovieRepository {

    override fun getPopularMovieList(): LiveData<PagingData<Results>> {

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiInterface, mapper)
            }
        ).liveData
    }

}