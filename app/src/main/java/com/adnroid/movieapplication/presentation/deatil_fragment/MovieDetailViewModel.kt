package com.adnroid.movieapplication.presentation.deatil_fragment

import androidx.lifecycle.ViewModel
import com.adnroid.movieapplication.di.IdQualifier
import com.pacckage.domain.DetailMovieUseCase
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val useCase: DetailMovieUseCase,
    @IdQualifier private val id: Int
): ViewModel() {



}