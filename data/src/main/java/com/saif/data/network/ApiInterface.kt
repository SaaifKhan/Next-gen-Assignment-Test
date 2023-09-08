package com.saif.data.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiInterface {

    @GET
    suspend fun get(@Url endpoint: String): Response<ResponseBody>

    @GET
    suspend fun get(
        @Url endpoint: String,
        @QueryMap query: @JvmSuppressWildcards Map<String, String>
    ): Response<ResponseBody>

}