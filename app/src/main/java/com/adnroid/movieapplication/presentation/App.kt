package com.adnroid.movieapplication.presentation

import android.app.Application
import com.adnroid.movieapplication.di.DaggerApplicationComponent

class App: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

}