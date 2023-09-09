    package com.saif.presenter.ui.movielist

    import android.view.View
    import androidx.lifecycle.LiveData
    import androidx.lifecycle.MutableLiveData
    import androidx.lifecycle.ViewModel
    import androidx.lifecycle.viewModelScope
    import com.paginate.Paginate
    import com.saif.base.Event
    import com.saif.base.extensions.default
    import com.saif.domain.exception.ConnectivityException
    import com.saif.domain.exception.ServerException
    import com.saif.domain.exception.UnProcessableEntityException
    import com.saif.domain.exception._404Exception
    import com.saif.domain.model.home.MovieRequest
    import com.saif.domain.usecases.GetAllMoviesUC
    import dagger.hilt.android.lifecycle.HiltViewModel
    import kotlinx.coroutines.flow.catch
    import kotlinx.coroutines.launch
    import org.json.JSONObject
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


        //for error handling for now
        private val _errorMessage = MutableLiveData<String>()
        val errorMessage: LiveData<String>
            get() = _errorMessage



        //
        fun setError(message: String) {
            _errorMessage.value = message
        }

        fun fetchSubscriptions() {
            showLoader(true)
            viewModelScope.launch {
                isLoading = true
                getAllMoviesUC(request).catch { exceptions ->
                    isLoading = false
                    hasLoadedAllItems = true
                    noDataVisibility.value = View.GONE
                    showLoader(false)
                    handleExceptions(exceptions)
                }.collect {
                    isLoading = false
                    showLoader(false)

                    hasLoadedAllItems = (it?.page?.toInt() ?: 1) >= (it?.total_pages?.toInt() ?: 1)
                    request.page = (it?.page?.toInt() ?: 1) + 1

                    _events.value = Event(MovieListEvents.GetMovieList(it?.results ?: emptyList()))
                    if ((it?.page ?: 1) == 1 && it?.results.isNullOrEmpty())
                        noDataVisibility.value = View.GONE
                    else
                        noDataVisibility.value = View.VISIBLE
                    showLoader(false)

                }
            }
        }

        override fun onLoadMore() {
            fetchSubscriptions()
        }

        override fun isLoading() = isLoading

        override fun hasLoadedAllItems()= hasLoadedAllItems


        private fun showLoader(show: Boolean){
            _events.value = Event(MovieListEvents.Loading(show))
        }

        //handling exceptions for now because i didnt make base viewmodel for now but this should be there
        fun handleExceptions(exception: Throwable): HashMap<String, String> {
            val errors: HashMap<String, String> = HashMap()
            when (exception) {
                is ConnectivityException, is _404Exception, is ServerException -> {
                    setError(parseErrors(exception.message ?: ""))
                    errors.put("error", exception.message ?: "")
                }
                is UnProcessableEntityException -> {
                    errors.putAll(exception.errorMap)
                    exception.errorMap.forEach {
                        setError(it.value)

                    }
                }
                else -> {
                    setError(parseErrors(exception.message ?: ""))
                    errors.put("error", exception.message ?: "")
                }
            }
            return errors
        }

    }


    //should be viewmodel base but fr now
    private fun parseErrors(
        errorResponse: String
    ): String {
        try {
            var err: JSONObject = JSONObject(errorResponse)
            if (err.has("message"))
                return err.get("message").toString()

            if (err.has("error"))
                return err.get("error").toString()

            if (err.has("msg"))
                return err.get("msg").toString()

            return "Something went wrong"
        }
        catch (e:Exception){
            return "Something went wrong"
        }
    }




