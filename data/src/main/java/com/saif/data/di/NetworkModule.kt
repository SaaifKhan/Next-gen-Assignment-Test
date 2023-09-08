package com.saif.data.di


import com.saif.data.network.ApiInterface
import com.saif.data.network.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(
        requestInterceptor: RequestInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(requestInterceptor)
        .writeTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()


    @Provides
    @Singleton
    fun provideApiInterface(@NetworkServiceQualifier retrofit: Retrofit): ApiInterface =
        retrofit.create<ApiInterface>(ApiInterface::class.java)

}