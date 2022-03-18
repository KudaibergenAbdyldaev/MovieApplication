package com.pacckage.domain

import javax.inject.Inject

class DetailMovieUseCase @Inject constructor(
    private val repository: DetailRepository
) {

    suspend operator fun invoke(movieId: Int) = repository.getDetailMovie(movieId)

}