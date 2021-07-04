package com.example.remoteDataSource

import com.example.Util.EntityMapper
import com.example.hiltdi.model.Blog
import javax.inject.Inject

class NetworkMapper
@Inject
constructor() : EntityMapper<BlogNetworkEntity, Blog> {
    override fun mapFromEntity(entity: BlogNetworkEntity): Blog {
        return Blog(
            entity.id,
            entity.title,
            entity.body,
            entity.image,
            entity.category
        )
    }

    override fun mapToEntity(domainModel: Blog): BlogNetworkEntity {
        return BlogNetworkEntity(
            domainModel.id,
            domainModel.title,
            domainModel.body,
            domainModel.catgory,
            domainModel.image
        )
    }

    fun mapFromList(entities:List<BlogNetworkEntity>):List<Blog>{

        return entities.map{ mapFromEntity(it) }
    }


}