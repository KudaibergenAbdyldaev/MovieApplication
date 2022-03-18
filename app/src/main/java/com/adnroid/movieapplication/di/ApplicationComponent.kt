package com.adnroid.movieapplication.di

import android.app.Application
import com.adnroid.movieapplication.presentation.App
import com.adnroid.movieapplication.presentation.main.MainActivity
import com.adnroid.movieapplication.presentation.popular_fragment.PopularFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(app: App)
    fun inject(activity: MainActivity)
    fun inject(popularFragment: PopularFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}
