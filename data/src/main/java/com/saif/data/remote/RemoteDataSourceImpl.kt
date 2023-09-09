package com.saif.data.remote

import com.saif.data.network.API
import com.saif.data.network.NetworkCall
import com.saif.domain.model.home.Movie
import com.saif.domain.model.home.MovieDetailsRequest
import com.saif.domain.model.home.MovieDetailsResponse
import com.saif.domain.model.home.MovieRequest
import com.saif.domain.model.home.PaginationResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val networkCall: NetworkCall
):RemoteDataSource{
    override suspend fun getAllMovies(request: MovieRequest): Flow<PaginationResponse<Movie>?> {
        val hashmap = HashMap<String,String>().apply {
            put("page","${request.page}")
            put("language",request.language)
        }
        return networkCall.get(API.MOVIES_LIST, queryMap = hashmap)
    }

    override suspend fun getMovieDetails(movieDetailsRequest: MovieDetailsRequest): Flow<MovieDetailsResponse?> {
        val query = HashMap<String,String>().apply {
            put("language", movieDetailsRequest.language)
        }
        return networkCall.get<MovieDetailsResponse>(API.MOVIE_DETAILS.replace(oldValue = "{movie_id}", newValue = movieDetailsRequest.movieId),query)

    }
}