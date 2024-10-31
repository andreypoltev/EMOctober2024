package ru.andreypoltev.em202410.domain

import ru.andreypoltev.em202410.data.model.APIResponse

interface APIRepository {
    suspend fun fetchApiResponse(): APIResponse
}