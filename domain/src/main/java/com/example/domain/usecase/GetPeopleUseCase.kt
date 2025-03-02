package com.example.domain.usecase

import com.example.domain.entity.StarWarsCharacter
import com.example.domain.repository.SWAppRepository

class GetPeopleUseCase(private val repository: SWAppRepository) {
    suspend operator fun invoke(): List<StarWarsCharacter> = repository.getPeopleList()
}