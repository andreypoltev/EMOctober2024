package ru.andreypoltev.em202410.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.andreypoltev.em202410.MainViewModel
import ru.andreypoltev.em202410.VacancyCard

@Composable
fun FavoritesScreen(viewModel: MainViewModel) {

    val vacancies by viewModel.filteredVacancies.collectAsState(emptyList())

    Scaffold(modifier = Modifier.fillMaxSize()) {

        if (vacancies.isEmpty()) {

            Box(modifier = Modifier.fillMaxSize())

        } else {


            LazyColumn {

                items(vacancies) { vacancy ->

                    VacancyCard(vacancy, {

                    }, onToggleFavoriteClicked = { viewModel.toggleFavorite(vacancy.id) })
                }
            }

        }
    }


}