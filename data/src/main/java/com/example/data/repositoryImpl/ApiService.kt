package com.example.data.repositoryImpl

import com.example.data.entity.StarWarsCharacterDto
import com.example.data.entity.StarWarsPeopleDto
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class ApiService(private val client: HttpClient) {

    private val BASE_URL = "https://swapi.dev/api/"
    private val ENDPOINT = "people/"

//    val client = HttpClient(CIO) {
//        install(ContentNegotiation) {
//            json(Json {
//                prettyPrint = true
//                isLenient = true
//                ignoreUnknownKeys = true
//            })
//        }
//    }

    suspend fun fetchCharacterById(characterId: String): StarWarsCharacterDto {
        return client.get("$BASE_URL$ENDPOINT$characterId").body()
    }

    suspend fun fetchPeopleList(): StarWarsPeopleDto {
        return client.get("$BASE_URL$ENDPOINT").body()
    }
}
