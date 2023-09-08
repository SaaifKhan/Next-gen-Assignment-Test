package com.saif.data.network

import com.saif.data.util.Applog
import com.saif.data.util.InternetConnection
import com.saif.domain.exception.ConnectivityException
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RequestInterceptor @Inject constructor(
    private var internetConnection: InternetConnection,
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        if (!internetConnection.isNetworkConnected()) {
            throw ConnectivityException()
        }

        val requestBuilder = chain.request().newBuilder()
        requestBuilder.addHeader("Accept", "application/json")
        runBlocking {
            //this can be changed to get it from server and store it into data store manager
            val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJlMDU3ZDZkMjYxM2Y5YjYzYWNhNjExZTM0MzlhZTAzNCIsInN1YiI6IjY0NjVkZjZmMmJjZjY3MDExYmYxYzQzZiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.tOrVTTgx2gSCsSBhQKTb5koWLMPXb_qlfYbeWdWdCTk"
            requestBuilder.addHeader("Authorization",token)
        }

        val request = requestBuilder.build()

        Applog.d("endpoint: ${request.url().url()}")
        Applog.d("headerMap: ${request.headers()}")
        Applog.d("queryMap: ${request.url().query()}")
        Applog.d("bodyMap: ${request.body()}")

        return chain.proceed(request)
    }
}