package com.pacckage.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.*
import com.pacckage.data.local_db.MovieDataBase
import com.pacckage.data.mapper.MovieMapper
import com.pacckage.data.network.ApiInterface
import com.pacckage.domain.MovieRepository
import com.pacckage.domain.Results
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val mapper: MovieMapper,
    private val db: MovieDataBase
) : MovieRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularMovieList(): LiveData<PagingData<Results>> {

        return Pager(
            config = PagingConfig(
                pageSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { db.movieDao().getPopularMovie() },
            remoteMediator = MovieRemoteMediator(apiInterface, mapper, db)
        ).liveData
            .map { pagedData ->
                pagedData.map { mapper.mapResultsEntityToResults(it) }
            }
    }

}