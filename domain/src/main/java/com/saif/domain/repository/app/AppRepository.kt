package com.saif.domain.repository.app

import com.saif.domain.model.home.Movie
import com.saif.domain.model.home.MovieDetailsRequest
import com.saif.domain.model.home.MovieDetailsResponse
import com.saif.domain.model.home.MovieRequest
import com.saif.domain.model.home.PaginationResponse
import kotlinx.coroutines.flow.Flow

interface AppRepository  {

    suspend fun getMovies(movieRequest: MovieRequest) : Flow<PaginationResponse<Movie>?>
    suspend fun getMovieDetails(movieDetailsRequest: MovieDetailsRequest) : Flow<MovieDetailsResponse?>

}