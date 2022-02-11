package com.adnroid.movieapplication.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.adnroid.movieapplication.R
import com.adnroid.movieapplication.databinding.ActivityMainBinding
import com.adnroid.movieapplication.presentation.App
import com.adnroid.movieapplication.presentation.adapter.MovieAdapter
import com.adnroid.movieapplication.presentation.view_model_factory.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val component by lazy {
        (application as App).component
    }

    private val movieAdapter by lazy {
        MovieAdapter()
    }

    private lateinit var viewModel: MainViewModel
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.recyclerView.adapter = movieAdapter

        viewModel.getPopularMovie()

        viewModel.popularMovie.observe(this) {
            if (it!=null){
                Log.d("GetPopularMovie", it.toString())
                movieAdapter.submitData(lifecycle, it)
            }
        }

    }
}