package ru.andreypoltev.em202410.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.andreypoltev.em202410.navigation.AppDestinations

@Composable
fun ApplicationsScreen(currentDestination: AppDestinations) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Text(currentDestination.name)

    }
}