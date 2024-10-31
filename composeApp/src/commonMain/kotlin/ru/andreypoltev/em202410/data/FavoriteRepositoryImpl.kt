package ru.andreypoltev.em202410.data

import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import ru.andreypoltev.em202410.data.model.Vacancy
import ru.andreypoltev.em202410.domain.FavoriteRepository


// RealmDataSource.kt (Data Layer)
class FavoriteRepositoryImpl : FavoriteRepository {

    private val favorites by lazy { initializeRealm() }

    private fun initializeRealm(): Realm {
        return Realm.open(
            RealmConfiguration.Builder(setOf(Favorite::class)).name("favorites.realm").build()
        )
    }

    override suspend fun toggleFavorite(id: String) {
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

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getFavoriteIds(): Flow<Set<String>> {
        return favorites.query<Favorite>().asFlow()
            .mapLatest { results -> results.list.map { it.id }.toSet() }
            .flowOn(Dispatchers.IO)
    }



    @OptIn(ExperimentalCoroutinesApi::class)
    override fun filterFavoritePositions(vacancies: List<Vacancy>): Flow<List<Vacancy>> {
        return favorites.query<Favorite>().asFlow()
            .mapLatest { results ->
                val favoriteIds = results.list.map { it.id }.toSet()
                vacancies.filter { it.id in favoriteIds }
            }
            .flowOn(Dispatchers.IO)
    }
}