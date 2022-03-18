package com.adnroid.movieapplication.presentation.popular_fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.adnroid.movieapplication.R
import com.adnroid.movieapplication.databinding.FragmentPopularBinding
import com.adnroid.movieapplication.presentation.App
import com.adnroid.movieapplication.presentation.adapter.LoaderStateAdapter
import com.adnroid.movieapplication.presentation.adapter.MovieAdapter
import com.adnroid.movieapplication.presentation.deatil_fragment.DetailMovieFragment
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

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter()
    }

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

        setUpRecyclerView()
        setRecyclerViewData()
        openMovieDetail()
    }

    private fun setRecyclerViewData() {
        lifecycleScope.launch {
            viewModel.getPopularMovie().observe(viewLifecycleOwner) {
                movieAdapter.submitData(lifecycle, pagingData = it)
            }
        }
    }

    private fun setUpRecyclerView() {
        val headerAdapter = LoaderStateAdapter()
        val footerAdapter = LoaderStateAdapter()

        val concatAdapter = movieAdapter.withLoadStateHeaderAndFooter(
            header = headerAdapter,
            footer = footerAdapter
        )
        binding.recyclerView.adapter = concatAdapter
        val layoutManager = GridLayoutManager(requireContext(), 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == 0 && headerAdapter.itemCount > 0) {
                    2
                } else if (position == concatAdapter.itemCount - 1 && footerAdapter.itemCount > 0) {
                    // if it is the last position and we have a footer
                    2
                } else {
                    1
                }
            }
        }
        binding.recyclerView.layoutManager = layoutManager
    }

    private fun openMovieDetail(){
        movieAdapter.onMovieItemClickListener = {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailMovieFragment())
                .addToBackStack(null)
                .commit()
        }
    }
}