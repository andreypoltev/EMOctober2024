package ru.andreypoltev.em202410.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class APIResponse(
    @SerialName("offers")
    val offers: List<Offer> = listOf(),
    @SerialName("vacancies")
    val vacancies: List<Vacancy> = listOf()
)