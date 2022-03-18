package com.pacckage.domain

interface DetailRepository {

    suspend fun getDetailMovie(id: Int): DetailMovie

}