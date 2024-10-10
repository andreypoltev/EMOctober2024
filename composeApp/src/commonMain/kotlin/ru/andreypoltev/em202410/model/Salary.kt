package ru.andreypoltev.em202410.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Salary(
    @SerialName("full")
    val full: String = "",
    @SerialName("short")
    val short: String = ""
)