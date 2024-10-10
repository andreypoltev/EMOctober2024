package ru.andreypoltev.em202410.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Experience(
    @SerialName("previewText")
    val previewText: String = "",
    @SerialName("text")
    val text: String = ""
)