package ru.andreypoltev.em202410.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.andreypoltev.em202410.DetailsPane
import ru.andreypoltev.em202410.ListPane
import ru.andreypoltev.em202410.MainViewModel
import ru.andreypoltev.em202410.model.Vacancy

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SearchScreen(

    viewModel: MainViewModel = viewModel {
        MainViewModel()
    }

) {


    val navigator = rememberListDetailPaneScaffoldNavigator<Vacancy>()
//
//        Navigate()
//        BackHandler(navigator.canNavigateBack()) {
//            navigator.navigateBack()
//        }

    val apiResponse by viewModel.apiResponse.collectAsState()

    ListDetailPaneScaffold(directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {

            AnimatedPane {
                ListPane(apiResponse = apiResponse, onItemClicked = { item ->

                    navigator.navigateTo(
                        ListDetailPaneScaffoldRole.Detail,
                        item as Vacancy?
                    )


                })
            }
        },

        detailPane = {

            AnimatedPane {
                navigator.currentDestination?.content?.let {
                    DetailsPane(it) {
                        navigator.navigateTo(ListDetailPaneScaffoldRole.List)
                    }
                }
            }


        }, modifier = Modifier.fillMaxSize())
}