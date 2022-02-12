package com.adnroid.movieapplication.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.adnroid.movieapplication.databinding.ActivityMainBinding
import com.adnroid.movieapplication.presentation.App
import com.adnroid.movieapplication.presentation.adapter.MovieAdapter
import com.adnroid.movieapplication.presentation.view_model_factory.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val component by lazy {
        (application as App).component
    }

    private var movieAdapter: MovieAdapter? = null

    private lateinit var viewModel: MainViewModel
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        movieAdapter = MovieAdapter()
        binding.recyclerView.adapter = movieAdapter

        lifecycleScope.launch {
            viewModel.getPopularMovie().observe(this@MainActivity){
                movieAdapter?.submitData(lifecycle,pagingData = it)
            }
        }

        setContentView(binding.root)
    }
}