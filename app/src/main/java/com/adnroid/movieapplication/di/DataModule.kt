package com.adnroid.movieapplication.di


import com.pacckage.data.mapper.MovieMapper
import com.pacckage.data.network.ApiFactory
import com.pacckage.data.network.ApiInterface
import com.pacckage.data.repository.DetailRepositoryImpl
import com.pacckage.data.repository.MovieRepositoryImpl
import com.pacckage.domain.DetailRepository
import com.pacckage.domain.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindMovieRepository(impl: MovieRepositoryImpl): MovieRepository

    @Binds
    @ApplicationScope
    fun bindDetailMovieRepository(impl: DetailRepositoryImpl): DetailRepository

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
