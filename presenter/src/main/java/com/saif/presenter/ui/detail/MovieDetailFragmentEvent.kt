package com.saif.presenter.ui.detail

import com.saif.presenter.ui.movielist.MovieListEvents

sealed class MovieDetailFragmentEvent {
    class Loading(val isLoading : Boolean) : MovieDetailFragmentEvent()

}