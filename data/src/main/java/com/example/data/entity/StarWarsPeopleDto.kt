package com.example.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class StarWarsPeopleDto(
    val results: List<StarWarsCharacterDto>
)