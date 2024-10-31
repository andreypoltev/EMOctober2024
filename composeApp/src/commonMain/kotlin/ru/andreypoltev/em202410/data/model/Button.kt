package ru.andreypoltev.em202410.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Button(
    @SerialName("text")
    val text: String = ""
)