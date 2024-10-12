package ru.andreypoltev.em202410

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
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.andreypoltev.em202410.model.Vacancy
import ru.andreypoltev.em202410.theme.Green
import ru.andreypoltev.em202410.theme.Grey0
import ru.andreypoltev.em202410.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsPane(vacancy: Vacancy, onNavigateBackClicked: () -> Unit) {

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        Icon(Icons.Default.Favorite, null)
        TopAppBar(
            title = {},
            navigationIcon = {

                IconButton(onClick = onNavigateBackClicked) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, "Go Back")
                }

            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.CheckBox, "Go Back")
                }

                IconButton(onClick = {}) {
                    Icon(Icons.Default.Share, "Go Back")
                }

                IconButton(onClick = {}) {
                    Icon(Icons.Default.Favorite, "Go Back")
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                navigationIconContentColor = White,
                actionIconContentColor = White
            )
        )
    }, containerColor = Grey0) {
        Column(modifier = Modifier.fillMaxWidth()) {

            Text(vacancy.title)
        }

    }


}