package com.saif.presenter.ui.movielist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paginate.Paginate
import com.saif.base.AdapterClickListener
import com.saif.domain.model.home.Movie
import com.saif.presenter.R
import com.saif.presenter.databinding.FragmentMovieListBinding
import com.saif.presenter.ui.detail.MovieDetailFragmentArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment(R.layout.fragment_movie_list), AdapterClickListener<Movie> {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding

    lateinit var adapter: MovieListingAdapter
    lateinit var layoutManager: LinearLayoutManager

    private val viewModel: MovieListViewModel by viewModels()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieListBinding.bind(view)
        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        initSubscriptionAdapter(binding?.rvMovieList)
        startObservers()



    }

    private fun initSubscriptionAdapter(view: RecyclerView?) {
        try {
            adapter = MovieListingAdapter(this)

            view?.adapter = adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            view?.layoutManager = layoutManager
            Paginate.with(view, viewModel)
                .setLoadingTriggerThreshold(2)
                .addLoadingListItem(true)
                .build()
        } catch (e: Exception) {
            print(e.printStackTrace())
        }

    }


    private fun startObservers() {
        viewModel.event.observe(viewLifecycleOwner){
            when(val event = it.getContentIfNotHandled()){

                is MovieListEvents.GetMovieList -> {
                    if (event.list?.isNotEmpty() == true){
                        adapter.appendList(event.list!!)
                    }
                }

                else -> {}
            }
        }

    }

    override fun onItemClick(item: Movie) {
        findNavController().navigate(MovieListFragmentDirections.showDetailFragment(item.id ?: 0))

    }



}