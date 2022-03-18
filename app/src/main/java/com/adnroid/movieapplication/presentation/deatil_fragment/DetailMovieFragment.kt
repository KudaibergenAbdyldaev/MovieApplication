package com.adnroid.movieapplication.presentation.deatil_fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adnroid.movieapplication.R
import com.adnroid.movieapplication.presentation.App


class DetailMovieFragment : Fragment() {

    private val component by lazy {
        (requireActivity().application as App).component
            .movieDetailComponent()
            .create(1)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

}