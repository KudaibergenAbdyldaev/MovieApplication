package com.adnroid.movieapplication.di

import androidx.lifecycle.ViewModel
import com.adnroid.movieapplication.di.ViewModelKey
import com.adnroid.movieapplication.presentation.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
