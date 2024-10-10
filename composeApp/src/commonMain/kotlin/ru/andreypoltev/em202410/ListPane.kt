package ru.andreypoltev.em202410

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.andreypoltev.em202410.model.APIResponse

@Composable
fun ListPane(apiResponse: APIResponse, onItemClicked: (Any?) -> Unit) {


    Column(modifier = Modifier.fillMaxSize()) {

        LazyRow(modifier = Modifier.fillMaxWidth()) {

            items(apiResponse.offers) {

                Card {

                    Text(it.title)
                }


            }

        }

        LazyColumn(modifier = Modifier.fillMaxWidth()) {

            items(apiResponse.vacancies) {
                Card(onClick = { onItemClicked(it) }) {

                    Text(it.title)
                }
            }

        }
    }


}