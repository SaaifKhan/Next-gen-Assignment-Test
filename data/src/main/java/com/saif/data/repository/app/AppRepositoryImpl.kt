package com.saif.data.repository.app

import com.saif.data.remote.RemoteDataSource
import com.saif.domain.model.home.Movie
import com.saif.domain.model.home.MovieDetailsRequest
import com.saif.domain.model.home.MovieDetailsResponse
import com.saif.domain.model.home.MovieRequest
import com.saif.domain.model.home.PaginationResponse
import com.saif.domain.repository.app.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
):AppRepository {
    override suspend fun getMovies(movieRequest: MovieRequest): Flow<PaginationResponse<Movie>?> =
        remoteDataSource.getAllMovies(movieRequest)

    override suspend fun getMovieDetails(movieDetailsRequest: MovieDetailsRequest): Flow<MovieDetailsResponse?> =
        remoteDataSource.getMovieDetails(movieDetailsRequest = movieDetailsRequest)
}