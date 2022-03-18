package com.adnroid.movieapplication.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.adnroid.movieapplication.R
import com.adnroid.movieapplication.databinding.ActivityMainBinding
import com.adnroid.movieapplication.presentation.App
import com.adnroid.movieapplication.presentation.adapter.PopularViewModel
import com.adnroid.movieapplication.presentation.adapter.MovieAdapter
import com.adnroid.movieapplication.presentation.popular_fragment.PopularFragment
import com.adnroid.movieapplication.presentation.view_model_factory.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val component by lazy {
        (application as App).component
    }


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, PopularFragment())
                .commit()
        }

        setContentView(binding.root)
    }
}