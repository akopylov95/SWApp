package swapplication

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
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
        Log.i("MyApp composable(ID2)", url )
        val response: HttpResponse = client.get(url)
        return response.body()
    }

    suspend fun fetchPeopleList(): StarWarsPeople {
        val url = "$BASE_URL$ENDPOINT"
        Log.i("MyApp API", client.get(url).body())
        return client.get(url).body()
    }
}
