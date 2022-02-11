package com.adnroid.movieapplication.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.*
import com.adnroid.movieapplication.data.network.ApiInterface
import com.adnroid.movieapplication.domain.MovieRepository
import com.adnroid.movieapplication.domain.Results
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : MovieRepository {

    override suspend fun getPopularMovieList(): LiveData<PagingData<Results>> {

//        Log.d("MovieRepositoryImpl", apiInterface.getPopularMovie(apiKey = "bd8c2d1b4af41b4060929a043be50eb6",
//            language = "ru-RU",1).toString())

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 2,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                MoviePagingSource(apiInterface)
            }
        ).liveData
    }

}