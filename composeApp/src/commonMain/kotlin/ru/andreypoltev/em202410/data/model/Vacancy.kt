package ru.andreypoltev.em202410.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vacancy(
    @SerialName("address")
    val address: Address = Address(),
    @SerialName("appliedNumber")
    val appliedNumber: Int = 0,
    @SerialName("company")
    val company: String = "",
    @SerialName("description")
    val description: String = "",
    @SerialName("experience")
    val experience: Experience = Experience(),
    @SerialName("id")
    val id: String = "",
    @SerialName("isFavorite")
    val isFavorite: Boolean = false,
    @SerialName("lookingNumber")
    val lookingNumber: Int = 0,
    @SerialName("publishedDate")
    val publishedDate: String = "",
    @SerialName("questions")
    val questions: List<String> = listOf(),
    @SerialName("responsibilities")
    val responsibilities: String = "",
    @SerialName("salary")
    val salary: Salary = Salary(),
    @SerialName("schedules")
    val schedules: List<String> = listOf(),
    @SerialName("title")
    val title: String = ""
)