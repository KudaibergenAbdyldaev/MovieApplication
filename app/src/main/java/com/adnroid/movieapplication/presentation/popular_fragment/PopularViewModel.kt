package com.adnroid.movieapplication.presentation.popular_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pacckage.domain.GetPopularUseCase
import com.pacckage.domain.Results
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val getPopularUseCase: GetPopularUseCase
) : ViewModel() {

    private var _popularMovie = MutableLiveData<PagingData<Results>?>()

    suspend fun getPopularMovie(): LiveData<PagingData<Results>>{
        val response = getPopularUseCase.getPopularMovie().cachedIn(viewModelScope)
        _popularMovie.value = response.value
        return response
    }

}