package ru.andreypoltev.em202410

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.delete
import io.realm.kotlin.ext.query
import io.realm.kotlin.ext.realmSetOf
import io.realm.kotlin.ext.toRealmSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
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



    private val favorites by lazy {
        initializeRealm()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val favsIds = favorites.query<Favorite>().asFlow().mapLatest { favorites ->
        favorites.list.map { it.id }.toSet()
    }.flowOn(Dispatchers.IO)

    @OptIn(ExperimentalCoroutinesApi::class)
    val filteredVacancies = favorites.query<Favorite>().asFlow().mapLatest { favorites ->
        val favoriteIds = favorites.list.map { it.id }.toSet()
        _apiResponse.value.vacancies.filter { it.id in favoriteIds }
    }.flowOn(Dispatchers.IO)


    private fun initializeRealm(): Realm {
        return Realm.open(
            RealmConfiguration.Builder(setOf(Favorite::class)).name("favorites.realm").build()
        )
    }

    fun toggleFavorite(id: String) {

        viewModelScope.launch(Dispatchers.IO) {
            favorites.write {
                val currentFav = favorites.query<Favorite>("id == $0", id).find().firstOrNull()

                if (currentFav != null) {

                    findLatest(currentFav)?.also {
                        delete(it)
                    }

                } else {

                    copyToRealm(

                        Favorite().apply {
                            this.id = id
                        }

                    )

                }
            }
        }





    }


    suspend fun getApiResponse(): APIResponse {

        println("getApiResponse()")

        try {
            val client = HttpClient {
                install(ContentNegotiation) {
                    json(Json {
                        ignoreUnknownKeys = true
                    })
                }
            }

            val response = client.get(Constants.API_LINK).bodyAsText()
            client.close()
            val z = Json.decodeFromString<APIResponse>(response)

            return z

        } catch (e: Exception) {
            println(e.message)
            return APIResponse()
        }
    }
}