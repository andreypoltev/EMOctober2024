package ru.andreypoltev.em202410.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Message
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import emoctober2024.composeapp.generated.resources.Res
import emoctober2024.composeapp.generated.resources.applications
import emoctober2024.composeapp.generated.resources.favorites
import emoctober2024.composeapp.generated.resources.messages
import emoctober2024.composeapp.generated.resources.profile
import emoctober2024.composeapp.generated.resources.search
import org.jetbrains.compose.resources.StringResource

enum class AppDestinations(
    val label: StringResource,
    val icon: ImageVector,
//    val contentDescription: StringResource
) {
    SEARCH(Res.string.search, Icons.Default.Search),
    FAVORITES(Res.string.favorites, Icons.Default.Favorite),
    APPLICATIONS(Res.string.applications, Icons.Default.MailOutline),
    MESSAGES(Res.string.messages, Icons.AutoMirrored.Filled.Message),
    PROFILE(Res.string.profile, Icons.Default.AccountBox),
}