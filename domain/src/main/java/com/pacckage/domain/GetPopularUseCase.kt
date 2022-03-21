package com.pacckage.domain

import javax.inject.Inject

class GetPopularUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    fun getPopularMovie() = repository.getPopularMovieList()
}