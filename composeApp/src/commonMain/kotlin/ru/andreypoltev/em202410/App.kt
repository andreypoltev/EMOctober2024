package ru.andreypoltev.em202410

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.andreypoltev.em202410.navigation.AppDestinations
import ru.andreypoltev.em202410.screens.ApplicationsScreen
import ru.andreypoltev.em202410.screens.FavoritesScreen
import ru.andreypoltev.em202410.screens.MessagesScreen
import ru.andreypoltev.em202410.screens.ProfileScreen

import ru.andreypoltev.em202410.screens.SearchScreen

@Composable
@Preview
fun App(


) {
    Theme {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(modifier = Modifier.fillMaxSize()) {

                Scaffold(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
                    AppContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AppContent() {

    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.SEARCH) }

    NavigationSuiteScaffold(

        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(icon = {
                    Icon(
                        it.icon, contentDescription = ""
                    )
                },
                    label = { Text(stringResource(it.label)) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it })
            }
        }, modifier = Modifier.fillMaxSize()

    ) {

        when (currentDestination) {
            AppDestinations.SEARCH -> {
                SearchScreen()
            }

            AppDestinations.FAVORITES -> {
                FavoritesScreen(currentDestination)
            }

            AppDestinations.APPLICATIONS -> {
                ApplicationsScreen(currentDestination)
            }

            AppDestinations.MESSAGES -> {
                MessagesScreen(currentDestination)
            }

            AppDestinations.PROFILE -> {
                ProfileScreen(currentDestination)
            }
        }

    }


}





