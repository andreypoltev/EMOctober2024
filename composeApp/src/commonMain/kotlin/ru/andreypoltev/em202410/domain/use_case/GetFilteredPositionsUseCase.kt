package ru.andreypoltev.em202410.domain.use_case

import kotlinx.coroutines.flow.Flow
import ru.andreypoltev.em202410.data.model.Vacancy
import ru.andreypoltev.em202410.domain.FavoriteRepository

class GetFilteredPositionsUseCase(private val repository: FavoriteRepository) {
    operator fun invoke(vacancies: List<Vacancy>): Flow<List<Vacancy>> {
        return repository.filterFavoritePositions(vacancies)
    }
}