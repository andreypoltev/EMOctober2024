package ru.andreypoltev.em202410

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.andreypoltev.em202410.model.APIResponse

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPane(apiResponse: APIResponse, onItemClicked: (Any?) -> Unit) {

    Column(modifier = Modifier.fillMaxSize()) {

        if (apiResponse.offers.isNotEmpty()) {
            LazyRow(modifier = Modifier.fillMaxWidth()) {

                items(apiResponse.offers) {

                    Card {

                        Text(it.title)
                    }


                }

            }
        }

        if (apiResponse.vacancies.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {

                items(apiResponse.vacancies) {
                    Card(onClick = { onItemClicked(it) }) {

                        Text(it.title)
                    }
                }

            }
        }


    }


}