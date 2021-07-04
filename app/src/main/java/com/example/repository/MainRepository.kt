package com.example.repository

import com.example.Util.Resource
import com.example.cacheDataSource.BlogDao
import com.example.cacheDataSource.CacheMapper

import com.example.hiltdi.model.Blog
import com.example.remoteDataSource.BlogRetrofit
import com.example.remoteDataSource.NetworkMapper
import com.example.remoteDataSource.WebService

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MainRepository
@Inject
constructor(
    private val blogDao: BlogDao,
    private val blogRetrofit: BlogRetrofit,
    private val cacheMapper: CacheMapper,
    private val networkMapper: NetworkMapper,
    private val service: WebService
) {
    suspend fun getBlog(): Flow<Resource<List<Blog>>> = flow {
        emit(Resource.Loading)
        delay(1000)
        try {
            val networkBlogs = blogRetrofit.getBlog()
            val blogs = networkMapper.mapFromList(networkBlogs)
            for (blog in blogs) {
                blogDao.insert(cacheMapper.mapToEntity(blog))
            }
            val cacheBlogs = blogDao.getBlogs()
            emit(Resource.Success(cacheMapper.mapFromCache(cacheBlogs)))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }


    }

    suspend fun addPost(post: Blog): Flow<Resource<Boolean>> = flow {
        try {
            val blog = service.addPost(post)
            emit(Resource.Success(blog))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        }

    }

}