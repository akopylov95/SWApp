package com.example.domain.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class StarWarsPeople(
    val results: List<StarWarsCharacter>
)