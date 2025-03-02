package com.example.data.repositoryImpl

import com.example.domain.entity.StarWarsCharacter
import com.example.domain.entity.StarWarsPeople

interface SWAppRepositoruInterface {
    suspend fun fetchPeopleList(): StarWarsPeople
    suspend fun fetchCharacterById(id: String): StarWarsCharacter
}