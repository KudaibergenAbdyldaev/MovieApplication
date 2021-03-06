package com.adnroid.movieapplication.di


import android.app.Application
import com.pacckage.data.local_db.MovieDataBase
import com.pacckage.data.mapper.MovieMapper
import com.pacckage.data.network.ApiFactory
import com.pacckage.data.network.ApiInterface
import com.pacckage.data.network.check_internet_connection.ConnectivityInterceptor
import com.pacckage.data.network.check_internet_connection.NetworkUtil
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
        fun provideApiService(connectivityInterceptor: ConnectivityInterceptor): ApiInterface {
            return ApiFactory(connectivityInterceptor).apiService
        }

        @Provides
        @ApplicationScope
        fun provideMapper(): MovieMapper {
            return MovieMapper()
        }

        @Provides
        @ApplicationScope
        fun provideMovieDataBase(
            application: Application
        ): MovieDataBase {
            return MovieDataBase.getInstance(application)
        }

        @Provides
        @ApplicationScope
        fun provideNetworkUtil(
            application: Application
        ): NetworkUtil {
            return NetworkUtil(application)
        }

        @Provides
        @ApplicationScope
        fun provideConnectivityInterceptor(
            networkUtil: NetworkUtil
        ): ConnectivityInterceptor {
            return ConnectivityInterceptor(networkUtil)
        }

    }
}
