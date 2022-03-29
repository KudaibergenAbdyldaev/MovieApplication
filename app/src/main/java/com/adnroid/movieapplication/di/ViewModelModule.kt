package com.adnroid.movieapplication.di

import androidx.lifecycle.ViewModel
import com.adnroid.movieapplication.presentation.popular_fragment.PopularViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    fun bindPopularViewModel(viewModel: PopularViewModel): ViewModel

}
