package com.adnroid.movieapplication.presentation.deatil_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.adnroid.movieapplication.databinding.FragmentDetailMovieBinding
import com.adnroid.movieapplication.di.MovieDetailComponent
import com.adnroid.movieapplication.presentation.App
import com.adnroid.movieapplication.presentation.extension.loadImageFromUrl
import com.adnroid.movieapplication.presentation.view_model_factory.ViewModelFactory
import javax.inject.Inject


class DetailMovieFragment : Fragment() {

    private var component: MovieDetailComponent? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieDetailViewModel

    private var _binding: FragmentDetailMovieBinding? = null
    private val binding: FragmentDetailMovieBinding
        get() = _binding ?: throw RuntimeException("FragmentDetailMovieBinding is null")

    private var movieId = UNDEFINED_ID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()

        component = (requireActivity().application as App).component
            .movieDetailComponent()
            .create(movieId)

        component?.inject(this)
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
        viewModel.detailMovie.observe(viewLifecycleOwner) {
            binding.imageView.loadImageFromUrl(it.images)
            binding.title.text = it.title
            binding.desk.text = it.overview
        }
    }

    private fun parseParams() {
        val args = requireArguments()

        if (!args.containsKey(DETAIL_MOVIE_ID)) {
            throw RuntimeException("Param movie id is absent")
        }
        movieId = args.getInt(DETAIL_MOVIE_ID, UNDEFINED_ID)

    }

    companion object {

        private const val DETAIL_MOVIE_ID = "detail_movie_id"
        private const val UNDEFINED_ID = 0

        fun newInstanceDetailMovieFragment(id: Int): DetailMovieFragment {
            return DetailMovieFragment().apply {
                arguments = Bundle().apply {
                    putInt(DETAIL_MOVIE_ID, id)
                }
            }
        }

    }

}