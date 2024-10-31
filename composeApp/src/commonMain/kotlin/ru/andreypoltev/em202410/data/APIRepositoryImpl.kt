package ru.andreypoltev.em202410.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import ru.andreypoltev.em202410.data.model.APIResponse
import ru.andreypoltev.em202410.domain.APIRepository
import ru.andreypoltev.em202410.util.Constants

// Data Layer: Repository Implementation (in data package)
class APIRepositoryImpl : APIRepository {
    override suspend fun fetchApiResponse(): APIResponse {



        val client = HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }

        return try {
            val response = client.get(Constants.API_LINK).bodyAsText()
            client.close()
            Json.decodeFromString(response)
        } catch (e: Exception) {
            println("Error fetching API response: ${e.message}")
            APIResponse() // Return an empty response or handle error as needed
        }
    }
}