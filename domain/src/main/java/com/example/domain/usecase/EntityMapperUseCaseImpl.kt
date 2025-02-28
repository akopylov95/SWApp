package com.example.domain.usecase

import com.example.domain.data.entity.StarWarsCharacter
import com.example.domain.data.entity.StarWarsPeople
import com.example.domain.data.repositoryImpl.SwapiRepositoryImpl
import com.example.domain.data.repositoryImpl.SwapiRepositoryInterface

class EntityMapperUseCaseImpl: EntityMapperUseCaseInterface {

    private val swapiRepository: SwapiRepositoryInterface = SwapiRepositoryImpl()

    override suspend fun fetchCharacterById(id: String): StarWarsCharacter {
        return swapiRepository.fetchCharacterById(id = id)
    }

    override suspend fun fetchPeopleList(): StarWarsPeople {
        return swapiRepository.fetchPeopleList()
    }
}