package com.example.domain.data.repositoryImpl

import com.example.domain.data.ApiService
import com.example.domain.data.entity.StarWarsCharacter
import com.example.domain.data.entity.StarWarsPeople

class SwapiRepositoryImpl: SwapiRepositoryInterface {

    val apiService = ApiService()

    override suspend fun fetchCharacterById(id: String): StarWarsCharacter {
        return apiService.fetchCharacterById(id = id)
    }

    override suspend fun fetchPeopleList(): StarWarsPeople {
        return apiService.fetchPeopleList()
    }
}