package ru.andreypoltev.em202410

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.andreypoltev.em202410.navigation.AppDestinations

import ru.andreypoltev.em202410.navigation.Routes

@Composable
@Preview
fun App(



) {
    Theme {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(modifier = Modifier.fillMaxSize()) {


                Scaffold(

                    modifier = Modifier.fillMaxSize().statusBarsPadding()
                ) {
                    AppContent()
                }

            }

        }


    }
}

@Composable
fun AppContent() {


    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.mainScreen) {

        composable(Routes.mainScreen) {
            MainScreen()
        }


    }
}

@Composable
fun MainScreen(

    mainViewModel: MainViewModel = viewModel {
        MainViewModel()
    }

) {


    Text("Main Screen")

    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.SEARCH) }

    NavigationSuiteScaffold(

        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = {
                        Icon(
                            it.icon,
                            contentDescription = ""
                        )
                    },
                    label = { Text(stringResource(it.label)) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it }
                )
            }
        }

    )




}
