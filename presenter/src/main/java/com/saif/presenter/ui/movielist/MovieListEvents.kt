package com.saif.presenter.ui.movielist

import com.saif.domain.model.home.Movie

sealed class MovieListEvents {
    class GetMovieList(var list: List<Movie>?) : MovieListEvents()

}