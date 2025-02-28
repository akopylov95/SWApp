package com.example.domain.data.entity

import kotlinx.serialization.Serializable

@Serializable
data class StarWarsCharacter(
    val name: String,
    val height: String,
    val mass: String,
    val gender: String,
    val url: String
) // TODO move to data layer

