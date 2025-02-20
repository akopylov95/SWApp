package swapplication

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.engine.cio.*

class ApiService {
    private val client = HttpClient(CIO)

    suspend fun fetchCharacter(): StarWarsCharacter {
        return client.get("https://swapi.dev/api/people/1").body()
    }
}