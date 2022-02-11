package com.adnroid.movieapplication.presentation.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.adnroid.movieapplication.data.repository.Status
import com.adnroid.movieapplication.domain.GetPopularUseCase
import com.adnroid.movieapplication.domain.Movie
import com.adnroid.movieapplication.domain.Results
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPopularUseCase: GetPopularUseCase
) : ViewModel() {

    private var _popularMovie = MutableLiveData<PagingData<Results>?>()

    val popularMovie: LiveData<PagingData<Results>?> = _popularMovie

    fun getPopularMovie() {
        viewModelScope.launch {
            getPopularUseCase.getPopularMovie(viewModelScope).observeForever {
                when (it.status) {
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        _popularMovie.postValue(it.data?.value)
                    }
                }
            }
        }
    }

}