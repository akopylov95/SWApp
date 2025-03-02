package com.example.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class StarWarsCharacter(
    val name: String,
    val height: String,
    val mass: String,
    val gender: String,
    val url: String,
    val characterId: String = url.split("/").filter { it.isNotEmpty() }.last() //  Получаем ID из URL
)