package com.example.domain.usecase

import com.example.domain.data.entity.StarWarsCharacter
import com.example.domain.data.entity.StarWarsPeople

interface EntityMapperUseCaseInterface {
    suspend fun fetchCharacterById(id: String): StarWarsCharacter
    suspend fun fetchPeopleList(): StarWarsPeople
}