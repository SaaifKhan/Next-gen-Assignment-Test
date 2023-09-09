package com.saif.presenter.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.saif.presenter.R
import com.saif.presenter.databinding.FragmentMovieDetailBinding
import com.saif.presenter.databinding.FragmentMovieListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment(R.layout.fragment_movie_detail) {

    private val viewModel : MovieDetailFragmentViewModel by viewModels()

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding
    private val args: MovieDetailFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieDetailBinding.bind(view)
        viewModel.handleArgs(args)
        binding?.viewmode = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        viewModel.getMovieDetailById()
        startObservers()
    }

    private fun startObservers() {

    }


}