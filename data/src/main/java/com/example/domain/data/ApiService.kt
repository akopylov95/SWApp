package com.example.domain.data

import com.example.domain.data.entity.StarWarsCharacter
import com.example.domain.data.entity.StarWarsPeople
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiService {

    private val BASE_URL = "https://swapi.dev/api/"
    private val ENDPOINT = "people/"

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchCharacterById(id: String): StarWarsCharacter {
        val url = "$BASE_URL$ENDPOINT/$id/"
        println("MyApp fetchCharacterById(ID2) = $url")
        val response: HttpResponse = client.get(url)
        return response.body()
    }

    suspend fun fetchPeopleList(): StarWarsPeople {
        val url = "$BASE_URL$ENDPOINT"
        val response: HttpResponse = client.get(url).body()
        println("MyApp fetchPeopleList = ${response}")
        return client.get(url).body()
    }
}
