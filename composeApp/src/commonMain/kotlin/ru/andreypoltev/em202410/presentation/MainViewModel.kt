package ru.andreypoltev.em202410.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import ru.andreypoltev.em202410.data.Favorite
import ru.andreypoltev.em202410.data.model.APIResponse
import ru.andreypoltev.em202410.data.model.Vacancy
import ru.andreypoltev.em202410.domain.use_case.GetApiResponseUseCase
import ru.andreypoltev.em202410.domain.use_case.ToggleFavoriteUseCase
import ru.andreypoltev.em202410.domain.use_case.UserUseCases
import ru.andreypoltev.em202410.util.Constants

class MainViewModel(
    private val useCases: UserUseCases
) : ViewModel(

) {

    private val _apiResponse = MutableStateFlow(APIResponse())
    val apiResponse = _apiResponse.asStateFlow()

    private val _favorites = MutableStateFlow(listOf(Favorite()))
    val favorites = _favorites.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _apiResponse.value = useCases.getApiResponse()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val numberOfFavorites = useCases.getFavoriteIds().mapLatest {
        it.size
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    val favsIds = useCases.getFavoriteIds().mapLatest { ids ->
        _apiResponse.value.vacancies.filter { it.id in ids }
    }.flowOn(Dispatchers.IO)


    fun toggleFavorite(id: String) {

        viewModelScope.launch(Dispatchers.IO) {
            useCases.toggleFavorite(id)

        }
    }
}