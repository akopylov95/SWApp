package com.example.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class StarWarsPeople(
    val results: List<StarWarsCharacter>
)