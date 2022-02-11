package com.adnroid.movieapplication.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.adnroid.movieapplication.R
import com.adnroid.movieapplication.presentation.App
import com.adnroid.movieapplication.presentation.view_model_factory.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val component by lazy {
        (application as App).component
    }

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.getPopularMovie(1)

        viewModel.popularMovie.observe(this) {
            if (it!=null){
                Log.d("GetPopularMovie", it.toString())
            }
        }

    }
}