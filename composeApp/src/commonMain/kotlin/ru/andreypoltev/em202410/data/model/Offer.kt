package ru.andreypoltev.em202410.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Offer(
    @SerialName("button")
    val button: Button = Button(),
    @SerialName("id")
    val id: String = "",
    @SerialName("link")
    val link: String = "",
    @SerialName("title")
    val title: String = ""
)