package com.example.cacheDataSource

import com.example.Util.EntityMapper
import com.example.hiltdi.model.Blog
import javax.inject.Inject

class CacheMapper
@Inject
constructor() : EntityMapper<BlogCacheEntity, Blog> {
    override fun mapFromEntity(entity: BlogCacheEntity): Blog {
        return Blog(
            entity.id,
            entity.title,
            entity.body,
            entity.image,
            entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogCacheEntity {
        return BlogCacheEntity(
            domainModel.id,
            domainModel.title,
            domainModel.body,
            domainModel.image,
            domainModel.catgory
        )
    }

    fun mapFromCache(entities:List<BlogCacheEntity>):List<Blog>{
        return  entities.map{ mapFromEntity(it)}
    }
}