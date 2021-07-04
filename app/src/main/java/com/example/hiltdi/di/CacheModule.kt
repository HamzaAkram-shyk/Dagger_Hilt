package com.example.hiltdi.di

import android.content.Context
import androidx.room.Room
import com.example.Util.EntityMapper
import com.example.cacheDataSource.BlogCacheEntity
import com.example.cacheDataSource.BlogDao
import com.example.cacheDataSource.BlogDatabase
import com.example.cacheDataSource.CacheMapper
import com.example.hiltdi.model.Blog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): BlogDatabase {
        return Room.databaseBuilder(
            context,
            BlogDatabase::class.java,
            BlogDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    @Singleton
    @Provides
    fun provideBlogDao(blogDatabase: BlogDatabase):BlogDao{
        return  blogDatabase.blogDao()
    }



}