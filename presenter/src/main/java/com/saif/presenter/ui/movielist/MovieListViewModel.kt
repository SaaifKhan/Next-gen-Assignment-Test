package com.saif.presenter.ui.movielist

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paginate.Paginate
import com.saif.base.Event
import com.saif.base.extensions.default
import com.saif.domain.model.home.MovieRequest
import com.saif.domain.usecases.GetAllMoviesUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    val getAllMoviesUC: GetAllMoviesUC
) : ViewModel(), Paginate.Callbacks {

    @JvmField
    var isLoading: Boolean = false
    var hasLoadedAllItems: Boolean = false
    val noDataVisibility = MutableLiveData<Int>().default(View.GONE)
    var request = MovieRequest(page = 1)


    private val _events = MutableLiveData<Event<MovieListEvents>>()
    val event : LiveData<Event<MovieListEvents>> = _events




    fun fetchSubscriptions() {
//        showLoader()
        viewModelScope.launch {
            isLoading = true
            getAllMoviesUC(request).catch { exceptions ->
                isLoading = false
                hasLoadedAllItems = true
//                hideLoader()
//                handleExceptions(exceptions)
            }.collect {
                isLoading = false
//                hideLoader()
                hasLoadedAllItems = (it?.page?.toInt() ?: 1) >= (it?.total_pages?.toInt() ?: 1)
                request.page = (it?.page?.toInt() ?: 1) + 1

                _events.value = Event(MovieListEvents.GetMovieList(it?.results ?: emptyList()))
                if ((it?.page ?: 1) == 1 && it?.results.isNullOrEmpty())
                    noDataVisibility.value = View.GONE
                else
                    noDataVisibility.value = View.VISIBLE
//                hideLoader()
            }
        }
    }

    override fun onLoadMore() {
        fetchSubscriptions()
    }

    override fun isLoading() =
        isLoading

    override fun hasLoadedAllItems()=
        hasLoadedAllItems

}