package com.example.hiltdi.di

import com.example.cacheDataSource.BlogDao
import com.example.cacheDataSource.CacheMapper

import com.example.remoteDataSource.BlogRetrofit
import com.example.remoteDataSource.NetworkMapper
import com.example.remoteDataSource.WebService
import com.example.repository.MainRepository

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        blogDao: BlogDao,
        blogRetrofit: BlogRetrofit,
        cacheMapper: CacheMapper,
        networkMapper: NetworkMapper,
        service: WebService
    ): MainRepository {
        return MainRepository(blogDao, blogRetrofit, cacheMapper, networkMapper, service)
    }

}