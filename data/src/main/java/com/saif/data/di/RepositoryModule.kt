package com.saif.data.di

import com.saif.data.remote.RemoteDataSource
import com.saif.data.remote.RemoteDataSourceImpl
import com.saif.data.repository.app.AppRepositoryImpl
import com.saif.domain.repository.app.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideMoviesRepository(appRepositoryImpl: AppRepositoryImpl) : AppRepository


    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

}