package ru.andreypoltev.em202410.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.andreypoltev.em202410.presentation.navigation.AppDestinations

@Composable
fun ProfileScreen(currentDestination: AppDestinations) {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Text(currentDestination.name)

    }
}