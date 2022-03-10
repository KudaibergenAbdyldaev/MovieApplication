package com.adnroid.movieapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.*
import com.adnroid.movieapplication.data.mapper.MovieMapper
import com.adnroid.movieapplication.data.network.ApiInterface
import com.adnroid.movieapplication.domain.MovieRepository
import com.adnroid.movieapplication.domain.Results
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val mapper: MovieMapper
) : MovieRepository {

    override suspend fun getPopularMovieList(): LiveData<PagingData<Results>> {

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