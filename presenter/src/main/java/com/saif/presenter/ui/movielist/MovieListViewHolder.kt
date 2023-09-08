package com.saif.presenter.ui.movielist

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.saif.base.AdapterClickListener
import com.saif.base.adapters.loadImage
import com.saif.domain.model.home.Movie
import com.saif.presenter.BuildConfig
import com.saif.presenter.databinding.ItemMovieListBinding

class MovieListViewHolder(
    private val listener: AdapterClickListener<Movie>,
    private val binding : ItemMovieListBinding

    ):RecyclerView.ViewHolder(binding.root) {

        fun bind(item:Movie){
            binding.model = item
            binding.listener = listener

            // Concatenate the base URL and poster_path
            val imageUrl = "${BuildConfig.IMAGE_URL}${item.poster_path}"

            loadImage(binding.movieImageView, imageUrl = imageUrl)

            binding.executePendingBindings()
        }
}