package com.example.Util

interface EntityMapper<E, DM> {
    fun mapFromEntity(entity: E): DM
    fun mapToEntity(domainModel: DM): E
}