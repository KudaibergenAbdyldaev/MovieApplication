package com.adnroid.movieapplication.presentation.deatil_fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adnroid.movieapplication.di.IdQualifier
import com.pacckage.domain.DetailMovie
import com.pacckage.domain.DetailMovieUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val useCase: DetailMovieUseCase,
    @IdQualifier private val id: Int
) : ViewModel() {

    private var _detailMovie = MutableLiveData<DetailMovie>()
    val detailMovie: LiveData<DetailMovie> = _detailMovie

    fun getMovieDetail() {
        viewModelScope.launch { _detailMovie.value = useCase.invoke(id) }
    }

}