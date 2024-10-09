package ru.andreypoltev.em202410

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform