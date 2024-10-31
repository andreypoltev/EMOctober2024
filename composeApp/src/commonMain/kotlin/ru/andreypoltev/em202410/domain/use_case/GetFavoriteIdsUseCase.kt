package ru.andreypoltev.em202410.domain.use_case

import kotlinx.coroutines.flow.Flow
import ru.andreypoltev.em202410.domain.FavoriteRepository


class GetFavoriteIdsUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(): Flow<Set<String>> {
        return repository.getFavoriteIds()
    }
}