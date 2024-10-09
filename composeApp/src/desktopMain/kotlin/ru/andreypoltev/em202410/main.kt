package ru.andreypoltev.em202410

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "EM October 2024",
    ) {
        App()
    }
}