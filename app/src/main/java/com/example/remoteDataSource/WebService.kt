package com.example.remoteDataSource

import com.example.hiltdi.model.Blog
import org.intellij.lang.annotations.Flow
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface WebService {
    @GET("post")
    suspend fun getPosts():List<BlogNetworkEntity>
    @POST("/post")
    suspend fun addPost( @Field("post") post:Blog):Boolean
}