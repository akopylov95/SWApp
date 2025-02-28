package com.example.domain.data.repositoryImpl

import com.example.domain.data.entity.StarWarsCharacter
import com.example.domain.data.entity.StarWarsPeople

interface SwapiRepositoryInterface {
    suspend fun fetchCharacterById(id: String): StarWarsCharacter
    suspend fun fetchPeopleList(): StarWarsPeople
}