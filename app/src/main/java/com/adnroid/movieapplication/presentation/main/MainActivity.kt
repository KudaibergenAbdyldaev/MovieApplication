package com.adnroid.movieapplication.presentation.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adnroid.movieapplication.R
import com.adnroid.movieapplication.databinding.ActivityMainBinding
import com.adnroid.movieapplication.presentation.App
import com.adnroid.movieapplication.presentation.adapter.MovieAdapter
import com.adnroid.movieapplication.presentation.view_model_factory.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val scope = CoroutineScope(Job() + Dispatchers.Main)

    var job: Job? = null

    private val component by lazy {
        (application as App).component
    }

    private var movieAdapter: MovieAdapter?=null

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

        movieAdapter = MovieAdapter()

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        binding.recyclerView.adapter = movieAdapter

        scope.launch {
            viewModel.getPopularMovie().observe(this@MainActivity){
                Log.d("GetPopularMovie", it.toString())
                movieAdapter?.submitData(lifecycle,pagingData = it)
            }
        }

    }
    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
        job = null
    }
}