package com.example.domain.repository

import com.example.domain.entity.StarWarsCharacter

interface SWAppRepository {
    suspend fun getPeopleList(): List<StarWarsCharacter>
    suspend fun getCharacterById(characterId: String): StarWarsCharacter
}