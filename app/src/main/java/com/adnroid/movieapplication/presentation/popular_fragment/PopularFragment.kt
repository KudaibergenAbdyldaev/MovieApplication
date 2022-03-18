package com.adnroid.movieapplication.presentation.popular_fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.adnroid.movieapplication.databinding.FragmentPopularBinding
import com.adnroid.movieapplication.presentation.App
import com.adnroid.movieapplication.presentation.adapter.MovieAdapter
import com.adnroid.movieapplication.presentation.adapter.PopularViewModel
import com.adnroid.movieapplication.presentation.view_model_factory.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject


class PopularFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as App).component
    }

    private var _binding: FragmentPopularBinding? = null
    private val binding: FragmentPopularBinding
        get() = _binding ?: throw RuntimeException("FragmentPopularBinding is null")

    private var movieAdapter: MovieAdapter? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: PopularViewModel


    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[PopularViewModel::class.java]

        movieAdapter = MovieAdapter()
        binding.recyclerView.adapter = movieAdapter

        lifecycleScope.launch {
            viewModel.getPopularMovie().observe(viewLifecycleOwner) {
                movieAdapter?.submitData(lifecycle, pagingData = it)
            }
        }
    }

}