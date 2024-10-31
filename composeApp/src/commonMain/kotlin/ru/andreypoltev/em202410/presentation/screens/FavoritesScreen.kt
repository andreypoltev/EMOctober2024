package ru.andreypoltev.em202410.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import emoctober2024.composeapp.generated.resources.Res
import emoctober2024.composeapp.generated.resources.favorites
import org.jetbrains.compose.resources.stringResource
import ru.andreypoltev.em202410.presentation.MainViewModel
import ru.andreypoltev.em202410.presentation.VacancyCard
import ru.andreypoltev.em202410.theme.Grey3

@Composable
fun FavoritesScreen(viewModel: MainViewModel) {

    val vacancies by viewModel.favsIds.collectAsState(emptyList())

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->

        Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {

            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Spacer(modifier = Modifier.size(32.dp))

                Text(
                    text = stringResource(Res.string.favorites),
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(Modifier.size(24.dp))
            }




            if (vacancies.isEmpty()) {

                Box(modifier = Modifier.fillMaxSize())

            } else {

                Text(
                    text = "${vacancies.size} вакансия",
                    modifier = Modifier.padding(horizontal = 16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Grey3
                )

                Spacer(modifier = Modifier.size(16.dp))

                LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {

                    items(vacancies) { vacancy ->

                        VacancyCard(
                            vacancy,
                            {

                            },
                            onToggleFavoriteClicked = { viewModel.toggleFavorite(vacancy.id) },
                            viewModel
                        )
                    }
                }

            }
        }

    }

}