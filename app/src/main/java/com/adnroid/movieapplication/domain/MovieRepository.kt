package com.adnroid.movieapplication.domain

interface MovieRepository {
    
    suspend fun getPopularMovie(page: Int): Movie
    
}