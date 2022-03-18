package com.adnroid.movieapplication.presentation.deatil_fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.adnroid.movieapplication.R
import com.adnroid.movieapplication.databinding.FragmentDetailMovieBinding
import com.adnroid.movieapplication.databinding.FragmentPopularBinding
import com.adnroid.movieapplication.presentation.App
import com.adnroid.movieapplication.presentation.popular_fragment.PopularViewModel
import com.adnroid.movieapplication.presentation.view_model_factory.ViewModelFactory
import javax.inject.Inject


class DetailMovieFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as App).component
            .movieDetailComponent()
            .create(833425)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieDetailViewModel

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding: FragmentDetailMovieBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailMovieBinding is null")

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[MovieDetailViewModel::class.java]
        viewModel.getMovieDetail()
        viewModel.detailMovie.observe(viewLifecycleOwner){
            Log.e("getDetailMovie", it.toString())
        }
    }

}