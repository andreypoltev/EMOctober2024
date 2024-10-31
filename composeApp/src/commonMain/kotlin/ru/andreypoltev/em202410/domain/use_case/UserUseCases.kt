package ru.andreypoltev.em202410.domain.use_case

data class UserUseCases(
    val getApiResponse: GetApiResponseUseCase,
    val toggleFavorite: ToggleFavoriteUseCase,
    val getFilteredPositions: GetFilteredPositionsUseCase,
    val getFavoriteIds: GetFavoriteIdsUseCase

)