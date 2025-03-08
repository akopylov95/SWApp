package com.example.data.repositoryImpl

import com.example.domain.entity.StarWarsCharacter
import com.example.domain.repository.SWAppRepository

class SWAppRepositoryImpl(private val apiService: ApiService) : SWAppRepository {
    override suspend fun getPeopleList(): List<StarWarsCharacter> {
        return apiService.fetchPeopleList().results.map { dto ->
            StarWarsCharacter(
                name = dto.name,
                height = dto.height,
                mass = dto.mass,
                gender = dto.gender,
                url = dto.url
            )
        }
    }

    override suspend fun getCharacterById(characterId: String): StarWarsCharacter {
        val dto = apiService.fetchCharacterById(characterId)
        return StarWarsCharacter(
            name = dto.name,
            height = dto.height,
            mass = dto.mass,
            gender = dto.gender,
            url = dto.url
        )
    }
}