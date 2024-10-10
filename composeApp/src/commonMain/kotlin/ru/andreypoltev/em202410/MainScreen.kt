package ru.andreypoltev.em202410

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.stringResource
import ru.andreypoltev.DetailPane
import ru.andreypoltev.em202410.model.Vacancy
import ru.andreypoltev.em202410.navigation.AppDestinations


@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainScreen(

    mainViewModel: MainViewModel = viewModel {
        MainViewModel()
    }

) {


    Text("Main Screen")

    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.SEARCH) }

    val apiResponse by mainViewModel.apiResponse.collectAsState()



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
        }

    ) {

//        Navigate()
//
        val navigator = rememberListDetailPaneScaffoldNavigator<Vacancy>()
//
//        BackHandler(navigator.canNavigateBack()) {
//            navigator.navigateBack()
//        }


        ListDetailPaneScaffold(directive = navigator.scaffoldDirective,
            value = navigator.scaffoldValue,
            listPane = {

                AnimatedPane {
                    ListPane(apiResponse = apiResponse, onItemClicked = { item ->

                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail,
                            item as Vacancy?
                        )


                    })
                }
            },

            detailPane = {

                AnimatedPane {
                    navigator.currentDestination?.content?.let {
                        DetailPane(it)
                    }
                }


            })
    }


}