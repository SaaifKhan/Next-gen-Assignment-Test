package com.saif.domain.usecases

import com.saif.domain.model.home.MovieDetailsRequest
import com.saif.domain.model.home.MovieDetailsResponse
import com.saif.domain.repository.app.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDetailUC @Inject constructor(
    var appRepository: AppRepository
): UseCase<MovieDetailsResponse?, MovieDetailsRequest>() {
    override suspend fun run(params: MovieDetailsRequest): Flow<MovieDetailsResponse?> {
        return appRepository.getMovieDetails(params)
    }
}