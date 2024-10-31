package ru.andreypoltev.em202410.domain

import kotlinx.coroutines.flow.Flow
import ru.andreypoltev.em202410.data.model.Vacancy

interface FavoriteRepository {
    suspend fun toggleFavorite(id: String)
    fun getFavoriteIds(): Flow<Set<String>>
    fun filterFavoritePositions(vacancies: List<Vacancy>): Flow<List<Vacancy>>

}