package com.saif.presenter.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.saif.base.extensions.default
import com.saif.data.util.Applog
import com.saif.domain.model.home.MovieDetailsRequest
import com.saif.domain.model.home.MovieDetailsResponse
import com.saif.domain.usecases.MovieDetailUC
import com.saif.presenter.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailFragmentViewModel @Inject constructor(
    private val movieDetailUC: MovieDetailUC
) : ViewModel() {
    private val roomId = MutableLiveData<Int>().default(0)

    val movieDetailData = MutableLiveData<MovieDetailsResponse>()

    val genreNames = MutableLiveData<String>()
    val title = MutableLiveData<String>()
    val productionCompanyNames = MutableLiveData<String>()
    val languages = MutableLiveData<String>()
    val backdropImageUrl = MutableLiveData<String>()

    val request = MovieDetailsRequest()


    fun handleArgs(args: MovieDetailFragmentArgs) {
        roomId.value = args.movieId
        request.movieId = roomId.value.toString()
    }

    fun getMovieDetailById() {
        viewModelScope.launch {
            movieDetailUC(request)
                .onStart {
                    Applog.d("$request")

                }
                .catch {
                    Applog.d("viewmodel","${it.message}")
                }
                .collect { res ->
                    Applog.d("viewmodel","${res}")

                    // Update the movieDetailData LiveData
                    movieDetailData.value = res

                    title.value = res?.title ?: ""
                    // Extract and filter genre names
                    val genreNamesList = res?.genres?.map { it.name } ?: emptyList()
                    genreNames.value = genreNamesList.joinToString(", ")

                    // Extract and filter production company names
                    val productionCompanyNamesList =
                        res?.production_companies?.map { it.name } ?: emptyList()
                    productionCompanyNames.value = productionCompanyNamesList.joinToString(", ")

                    val languagesNames = res?.spoken_languages?.map { it.name } ?: emptyList()
                    languages.value = languagesNames.joinToString(", ")
                    backdropImageUrl.value = "${BuildConfig.IMAGE_URL}${res?.backdrop_path}"

                    Applog.d("viewmodel","${movieDetailData.value}")
                    Applog.d("viewmodel","${languages.value}")
                    Applog.d("viewmodel","${productionCompanyNames.value}")

                }
        }
    }
}