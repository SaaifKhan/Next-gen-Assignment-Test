package com.saif.domain.usecases

import com.saif.domain.model.home.Movie
import com.saif.domain.model.home.MovieRequest
import com.saif.domain.model.home.PaginationResponse
import com.saif.domain.repository.app.AppRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMoviesUC @Inject constructor(
    var appRepository: AppRepository
): UseCase<PaginationResponse<Movie>?, MovieRequest>() {
    override suspend fun run(params: MovieRequest): Flow<PaginationResponse<Movie>?> =
        appRepository.getMovies(params)

}