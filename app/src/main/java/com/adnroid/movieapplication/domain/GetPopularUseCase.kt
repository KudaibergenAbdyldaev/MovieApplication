package com.adnroid.movieapplication.domain

import javax.inject.Inject

class GetPopularUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend fun getPopularMovie() = repository.getPopularMovieList()
}