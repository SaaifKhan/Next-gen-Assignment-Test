package com.saif.presenter.ui.movielist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paginate.Paginate
import com.saif.base.AdapterClickListener
import com.saif.base.extensions.show
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MovieListingAdapter(this)



    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMovieListBinding.bind(view)
        binding?.viewmodel = viewModel
        binding?.lifecycleOwner = viewLifecycleOwner

        initSubscriptionAdapter(binding?.rvMovieList)
        startObservers()

        restrictBackPress()




    }

    private fun initSubscriptionAdapter(view: RecyclerView?) {
        try {

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
                        adapter.addItems(event.list!!)
                    }
                }
                MovieListEvents.onRefreshList -> {
                    adapter.clearList()
                }
                is MovieListEvents.Loading -> {

                }

                else -> {}
            }
        }

    }

    override fun onItemClick(item: Movie) {
        findNavController().navigate(MovieListFragmentDirections.showDetailFragment(item.id ?: 0))

    }


    private fun restrictBackPress() {
        activity?.onBackPressedDispatcher?.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Handle the back button press here
                    // You can display a confirmation dialog or exit the app directly
                    // For simplicity, we'll exit the app directly
                    activity?.finish()
                }
            }
        )
    }


    private fun showLoader(isLoading: Boolean) = binding?.progress show isLoading
    private fun hideRecyclerView(hide: Boolean) = binding?.rvMovieList show hide.not()


}