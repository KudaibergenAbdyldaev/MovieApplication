package com.adnroid.movieapplication.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope

interface MovieRepository {

    suspend fun getPopularMovieList(viewModelScope: CoroutineScope): LiveData<PagingData<Results>>

}