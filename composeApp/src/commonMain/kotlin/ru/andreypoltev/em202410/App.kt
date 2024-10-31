package ru.andreypoltev.em202410

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import ru.andreypoltev.em202410.data.APIRepositoryImpl
import ru.andreypoltev.em202410.data.Favorite
import ru.andreypoltev.em202410.data.FavoriteRepositoryImpl
import ru.andreypoltev.em202410.domain.use_case.GetApiResponseUseCase
import ru.andreypoltev.em202410.domain.use_case.GetFavoriteIdsUseCase
import ru.andreypoltev.em202410.domain.use_case.GetFilteredPositionsUseCase
import ru.andreypoltev.em202410.domain.use_case.ToggleFavoriteUseCase
import ru.andreypoltev.em202410.domain.use_case.UserUseCases
import ru.andreypoltev.em202410.presentation.navigation.AppDestinations
import ru.andreypoltev.em202410.presentation.MainViewModel
import ru.andreypoltev.em202410.presentation.screens.ApplicationsScreen
import ru.andreypoltev.em202410.presentation.screens.FavoritesScreen
import ru.andreypoltev.em202410.presentation.screens.MessagesScreen
import ru.andreypoltev.em202410.presentation.screens.ProfileScreen

import ru.andreypoltev.em202410.presentation.screens.SearchScreen
import ru.andreypoltev.em202410.theme.Blue
import ru.andreypoltev.em202410.theme.Grey4
import ru.andreypoltev.em202410.theme.Red
import ru.andreypoltev.em202410.theme.Theme
import ru.andreypoltev.em202410.theme.White

@Composable
@Preview
fun App(


) {
    Theme {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Scaffold(modifier = Modifier.fillMaxSize().statusBarsPadding()) {

                Scaffold(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
                    AppContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun AppContent(

    viewModel: MainViewModel = viewModel {
        MainViewModel(
            useCases = UserUseCases(
                getApiResponse = GetApiResponseUseCase(APIRepositoryImpl()),
                toggleFavorite = ToggleFavoriteUseCase(FavoriteRepositoryImpl()),
                getFilteredPositions = GetFilteredPositionsUseCase(FavoriteRepositoryImpl()),
                getFavoriteIds = GetFavoriteIdsUseCase(FavoriteRepositoryImpl())
            )
        )
    }

) {

    var currentDestination by rememberSaveable { mutableStateOf(AppDestinations.SEARCH) }


    val favCount by viewModel.numberOfFavorites.collectAsState(0)
//    val favCount by viewModel.favsIds.collectAsState(emptySet())


    NavigationSuiteScaffold(

        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(icon = {

                    Box {


                        Icon(
                            vectorResource(it.icon), contentDescription = null
                        )

                        if (favCount != 0 && it == AppDestinations.FAVORITES) {

                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.align(Alignment.TopEnd).size(11.dp).clip(
                                    CircleShape
                                ).background(Red)
                            ) {
                                Text(
                                    text = favCount.toString(),
                                    color = White,
                                    fontSize = 7.sp,
                                    lineHeight = 7.sp,

                                    )
                            }


                        }


                    }


                }, label = {
                    Text(
                        text = stringResource(it.label),
                        maxLines = 1,
                        overflow = TextOverflow.Visible,
                        color = if (currentDestination == it) Blue else Grey4
                    )
                }, selected = it == currentDestination, onClick = { currentDestination = it })
            }
        }, modifier = Modifier.fillMaxSize()

    ) {

        when (currentDestination) {
            AppDestinations.SEARCH -> {
                SearchScreen(viewModel)
            }

            AppDestinations.FAVORITES -> {
                FavoritesScreen(viewModel)
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





