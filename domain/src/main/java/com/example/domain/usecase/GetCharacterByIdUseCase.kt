package com.example.domain.usecase

import com.example.domain.entity.StarWarsCharacter
import com.example.domain.repository.SWAppRepository

class GetCharacterByIdUseCase(private val repository: SWAppRepository) {
    suspend operator fun invoke(characterId: String): StarWarsCharacter = repository.getCharacterById(characterId)
}