package com.adnroid.movieapplication.presentation.popular_fragment

import androidx.lifecycle.*
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pacckage.domain.GetPopularUseCase
import com.pacckage.domain.Results
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val getPopularUseCase: GetPopularUseCase
) : ViewModel() {

    private val currentQuery = MutableLiveData(DEFAULT_QUERY)

    val popularMovie = currentQuery.switchMap {
        getPopularUseCase.getPopularMovie().cachedIn(viewModelScope)
    }


    companion object {
        private const val DEFAULT_QUERY = "movie"
    }

}