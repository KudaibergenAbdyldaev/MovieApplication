package com.adnroid.movieapplication.di

import androidx.lifecycle.ViewModel
import com.adnroid.movieapplication.presentation.deatil_fragment.MovieDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface SubComponentViewModel {
    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel::class)
    fun bindMovieDetailViewModel(viewModel: MovieDetailViewModel): ViewModel
}