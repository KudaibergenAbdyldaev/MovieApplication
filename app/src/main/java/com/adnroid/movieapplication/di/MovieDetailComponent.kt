package com.adnroid.movieapplication.di

import com.adnroid.movieapplication.presentation.deatil_fragment.DetailMovieFragment
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(
    modules = [SubComponentViewModel::class]
)
interface MovieDetailComponent {


    fun inject(detailMovieFragment: DetailMovieFragment)

    @Subcomponent.Factory
    interface Factory {

        fun create(
            @BindsInstance @IdQualifier id: Int
        ): MovieDetailComponent
    }
}