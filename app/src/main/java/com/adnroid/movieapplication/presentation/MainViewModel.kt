package com.adnroid.movieapplication.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnroid.movieapplication.data.repository.Status
import com.adnroid.movieapplication.domain.GetPopularUseCase
import com.adnroid.movieapplication.domain.Movie
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getPopularUseCase: GetPopularUseCase
) : ViewModel() {

    private var _popularMovie = MutableLiveData<Movie?>()

    val popularMovie: LiveData<Movie?> = _popularMovie

    fun getPopularMovie(page: Int) {
        viewModelScope.launch {
            getPopularUseCase.getPopularMovie(page).observeForever {
                when (it.status) {
                    Status.ERROR -> {
                    }
                    Status.LOADING -> {
                    }
                    Status.SUCCESS -> {
                        _popularMovie.postValue(it.data)
                    }
                }
            }
        }
    }

}