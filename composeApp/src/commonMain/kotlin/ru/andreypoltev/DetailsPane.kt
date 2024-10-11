package ru.andreypoltev

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.andreypoltev.em202410.model.Vacancy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPane(vacancy: Vacancy, onNavigateBackClicked: () -> Unit) {

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopAppBar(title = {}, navigationIcon = {

            IconButton(onClick = onNavigateBackClicked) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Go Back")
            }

        }, actions = {
            IconButton(onClick = {}) {
                Icon(Icons.Default.CheckBox, "Go Back")
            }

            IconButton(onClick = {}) {
                Icon(Icons.Default.Share, "Go Back")
            }

            IconButton(onClick = {}) {
                Icon(Icons.Default.Favorite, "Go Back")
            }
        })
    }) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Text(vacancy.title)
        }

    }


}