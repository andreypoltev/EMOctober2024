package ru.andreypoltev.em202410.domain.use_case

import ru.andreypoltev.em202410.data.model.APIResponse
import ru.andreypoltev.em202410.domain.APIRepository

// Domain Layer: Use Case (in domain package)
class GetApiResponseUseCase(private val apiRepository: APIRepository) {
    suspend operator fun invoke(): APIResponse {
        return apiRepository.fetchApiResponse()
    }
}