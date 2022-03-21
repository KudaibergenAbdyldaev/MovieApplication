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

    companion object {
        const val PAGE_SIZE = 20
    }

    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularMovieList(): LiveData<PagingData<Results>> {
        val pagingSourceFactory = { db.movieDao().getPopularMovie() }
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = PAGE_SIZE + (PAGE_SIZE * 2),
                enablePlaceholders = false
            ),
            pagingSourceFactory = pagingSourceFactory,
            remoteMediator = MovieRemoteMediator(apiInterface, mapper, db)
        ).liveData
            .map { pagedData ->
                pagedData.map { mapper.mapResultsEntityToResults(it) }
            }
    }

}