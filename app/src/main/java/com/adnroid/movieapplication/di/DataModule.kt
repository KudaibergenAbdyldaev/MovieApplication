package com.adnroid.movieapplication.di

import com.adnroid.movieapplication.data.mapper.MovieMapper
import com.adnroid.movieapplication.data.network.ApiFactory
import com.adnroid.movieapplication.data.network.ApiInterface
import com.adnroid.movieapplication.data.repository.MovieRepositoryImpl
import com.adnroid.movieapplication.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideApiService(): ApiInterface {
            return ApiFactory.apiService
        }

        @Provides
        @ApplicationScope
        fun provideMapper(): MovieMapper {
            return MovieMapper()
        }
    }
}
