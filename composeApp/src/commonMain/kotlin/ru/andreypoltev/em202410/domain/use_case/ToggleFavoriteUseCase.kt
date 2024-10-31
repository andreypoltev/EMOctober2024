package ru.andreypoltev.em202410.domain.use_case

import ru.andreypoltev.em202410.domain.FavoriteRepository

// Use Case
class ToggleFavoriteUseCase(
    private val favoriteRepository: FavoriteRepository
) {
    suspend operator fun invoke(id: String) {
        favoriteRepository.toggleFavorite(id)
    }
}