package swapplication

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class ApiService {

    private val BASE_URL = "https://swapi.dev/api/"
    private val ENDPOINT = "people/"
    private val CHARACTER_ID = "1"

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun fetchCharacter(): StarWarsCharacter {
        val url = "$BASE_URL$ENDPOINT$CHARACTER_ID/"
        return client.get(url).body()
    }
}