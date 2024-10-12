package ru.andreypoltev.em202410.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import emoctober2024.composeapp.generated.resources.Res
import emoctober2024.composeapp.generated.resources.applications
import emoctober2024.composeapp.generated.resources.favorites
import emoctober2024.composeapp.generated.resources.filter
import emoctober2024.composeapp.generated.resources.messages
import emoctober2024.composeapp.generated.resources.profile
import emoctober2024.composeapp.generated.resources.search
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class AppDestinations(
    val label: StringResource,
    val icon: DrawableResource,
//    val contentDescription: StringResource
) {
    SEARCH(Res.string.search, Res.drawable.filter),
    FAVORITES(Res.string.favorites, Res.drawable.favorites),
    APPLICATIONS(Res.string.applications, Res.drawable.applications),
    MESSAGES(Res.string.messages, Res.drawable.messages),
    PROFILE(Res.string.profile, Res.drawable.profile),
}