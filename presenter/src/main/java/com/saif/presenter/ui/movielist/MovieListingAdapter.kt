package com.saif.presenter.ui.movielist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.saif.base.AdapterClickListener
import com.saif.base.adapters.BaseAdapter
import com.saif.domain.model.home.Movie
import com.saif.presenter.databinding.ItemMovieListBinding

class MovieListingAdapter(
    private val listener:AdapterClickListener<Movie>
): BaseAdapter<Movie, MovieListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder =

        MovieListViewHolder(
            listener,
            ItemMovieListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) =
        holder.bind(data[position])
}