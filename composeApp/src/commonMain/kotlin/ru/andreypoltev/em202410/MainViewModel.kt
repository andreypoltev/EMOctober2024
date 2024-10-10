package ru.andreypoltev.em202410

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.handleCoroutineException
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import ru.andreypoltev.em202410.model.APIResponse
import ru.andreypoltev.em202410.util.Constants

class MainViewModel : ViewModel() {

    private val _apiResponse = MutableStateFlow(APIResponse())
    val apiResponse = _apiResponse.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _apiResponse.value = getApiResponse()
//
//            println(resp.toString())
        }
    }


    @OptIn(InternalAPI::class)
    suspend fun getApiResponse(): APIResponse {

        println("getApiResponse()")

        val client = HttpClient() {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                })
            }
        }

        val response = client.get(Constants.API_LINK).bodyAsText()

        val z = Json.decodeFromString<APIResponse>(response)


        client.close()

        println("Response status: ${response}")
        println("Response content: ${response.toString()}")

        return z


//        val response = client.get(Constants.API_LINK)
//
//        client.close()
//        println(response.content.toString())

//        return response.body<APIResponse>()

    }

}