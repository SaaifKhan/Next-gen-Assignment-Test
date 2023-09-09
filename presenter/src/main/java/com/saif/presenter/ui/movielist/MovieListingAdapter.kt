package com.saif.presenter.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.saif.base.AdapterClickListener
import com.saif.base.adapters.BaseAdapter
import com.saif.base.adapters.loadImage
import com.saif.domain.model.home.Movie
import com.saif.presenter.BuildConfig
import com.saif.presenter.R
import com.saif.presenter.databinding.ItemMovieListBinding
import kotlin.properties.Delegates

class MovieListingAdapter(
    private val listener: AdapterClickListener<Movie>,

): BaseAdapter<Movie,ItemMovieListBinding>(R.layout.item_movie_list){
    override fun onItemInflated(items: Movie, position: Int, binding: ItemMovieListBinding) {
        binding.model = items
        binding.listener = listener

        // Concatenate the base URL and poster_path
        val imageUrl = "${BuildConfig.IMAGE_URL}${items.poster_path}"
        loadImage(binding.movieImageView, imageUrl = imageUrl)


    }


}



