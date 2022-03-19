package com.pacckage.domain

import androidx.lifecycle.LiveData
import androidx.paging.PagingData

interface MovieRepository {

    fun getPopularMovieList(): LiveData<PagingData<Results>>

}