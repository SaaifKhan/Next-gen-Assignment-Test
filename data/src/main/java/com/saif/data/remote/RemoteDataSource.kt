package com.saif.data.remote

import com.saif.domain.model.home.Movie
import com.saif.domain.model.home.MovieRequest
import com.saif.domain.model.home.PaginationResponse
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun getAllMovies(request: MovieRequest) : Flow<PaginationResponse<Movie>?>
}