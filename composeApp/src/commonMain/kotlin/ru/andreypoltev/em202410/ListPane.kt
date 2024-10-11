package ru.andreypoltev.em202410

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import ru.andreypoltev.em202410.model.APIResponse
import ru.andreypoltev.em202410.model.Offer
import ru.andreypoltev.em202410.model.Vacancy

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPane(apiResponse: APIResponse, onItemClicked: (Any?) -> Unit) {

    Scaffold(topBar = {

        CustomSearchBar()


    }) {


        Column(modifier = Modifier.fillMaxSize().padding(it).padding(top = 16.dp)) {

            if (apiResponse.offers.isNotEmpty()) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    items(apiResponse.offers) {

                        OfferCard(it)

                    }
//
                    item {

                        Spacer(Modifier.size(8.dp))
                    }


                }
                Spacer(Modifier.size(16.dp))
            }



            if (apiResponse.vacancies.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    if (apiResponse.offers.isNotEmpty()) {
                        items(apiResponse.offers) {

                        }
                    }


                    items(apiResponse.vacancies) {
                        Card(onClick = { onItemClicked(it) }) {

                            VacancyCard(it)


//                        Text(it.title)
                        }
                    }

                    item {
                        Spacer(Modifier.size(0.dp))
                    }


                }
            }


        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar() {

    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {

        var expanded by rememberSaveable { mutableStateOf(false) }

        var text by rememberSaveable { mutableStateOf("") }

        DockedSearchBar(inputField = {
            SearchBarDefaults.InputField(
                query = text,
                onQueryChange = { text = it },
                onSearch = { expanded = false },
                expanded = expanded,
                onExpandedChange = { expanded = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Hinted search text") },
                leadingIcon = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                },
//            trailingIcon = { Icon(Icons.Default.MoreVert, contentDescription = null) },
            )
        },
            expanded = expanded,
            onExpandedChange = {

//            expanded = it

            },
            shape = RoundedCornerShape(12.dp),
            content = {})

        Spacer(Modifier.size(16.dp))

        Column {

            Icon(Icons.Default.Settings, "", modifier = Modifier.size(60.dp))
        }

    }

}

@Composable
fun OfferCard(offer: Offer) {

    Card(modifier = Modifier.size(width = 132.dp, height = 120.dp), onClick = {
        offer.link
    }) {

        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {

            if (offer.id.isNotEmpty()) {
                Icon(Icons.Default.Star, "", modifier = Modifier.size(32.dp))
                Spacer(Modifier.size(16.dp))
            }

            Text(offer.title)

            if (offer.button.text.isNotEmpty()) {
                Text(offer.button.text)
            }


        }

    }


}

@Composable
fun VacancyCard(vacancy: Vacancy) {
    Card(modifier = Modifier.fillMaxWidth()) {

        Box(modifier = Modifier.fillMaxWidth()) {

            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {

                Column {

                    Text("Сейчас просматривает ${vacancy.lookingNumber} человек")
                    Text(vacancy.title)
                    Text(vacancy.address.town)

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Text(vacancy.company)
                        Icon(Icons.Default.Check, "", modifier = Modifier.size(16.dp))

                    }
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.ShoppingBag, "", modifier = Modifier.size(16.dp))
                        Text(vacancy.experience.previewText)
                    }
                    Text("Опубликовано ${vacancy.publishedDate}")
                }


                Button(onClick = {

                }, modifier = Modifier.fillMaxWidth()) {
                    Text("Oткликнуться")
                }
            }

            IconButton(onClick = {

            }, modifier = Modifier.align(Alignment.TopEnd).padding(top = 4.dp, end = 4.dp)) {
                Icon(Icons.Default.Favorite, "")
            }

        }

    }
}

