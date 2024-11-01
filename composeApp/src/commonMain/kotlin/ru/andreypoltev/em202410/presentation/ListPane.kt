package ru.andreypoltev.em202410.presentation

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import emoctober2024.composeapp.generated.resources.Res
import emoctober2024.composeapp.generated.resources.checked
import emoctober2024.composeapp.generated.resources.distance
import emoctober2024.composeapp.generated.resources.filter
import emoctober2024.composeapp.generated.resources.heart_filled
import emoctober2024.composeapp.generated.resources.heart_outlined
import emoctober2024.composeapp.generated.resources.level_up
import emoctober2024.composeapp.generated.resources.search
import emoctober2024.composeapp.generated.resources.sort_icon
import emoctober2024.composeapp.generated.resources.temp_job
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.vectorResource
import ru.andreypoltev.em202410.data.model.APIResponse
import ru.andreypoltev.em202410.data.model.Offer
import ru.andreypoltev.em202410.data.model.Vacancy
import ru.andreypoltev.em202410.theme.Blue
import ru.andreypoltev.em202410.theme.DarkBlue
import ru.andreypoltev.em202410.theme.DarkGreen
import ru.andreypoltev.em202410.theme.Green
import ru.andreypoltev.em202410.theme.Grey1
import ru.andreypoltev.em202410.theme.Grey2
import ru.andreypoltev.em202410.theme.Grey3
import ru.andreypoltev.em202410.theme.Grey4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListPane(
    apiResponse: APIResponse,
    onItemClicked: (Any?) -> Unit,
    viewModel: MainViewModel,
    onToggleFavoriteClicked: (id: String) -> Unit
) {

    var suitablePositions by rememberSaveable {
        mutableStateOf(false)
    }


    val positions = apiResponse.vacancies.take(3)

    Scaffold(topBar = {

        CustomSearchBar()
    }, bottomBar = {
        if (!suitablePositions && apiResponse.vacancies.isNotEmpty()) {
            Button(
                onClick = {
                    suitablePositions = true
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                content = {


                    Text(
                        text="Ещё ${(apiResponse.vacancies.size - 3).toString()} вакансии",
                        modifier = Modifier.padding(vertical = 8.dp), style = MaterialTheme.typography.titleSmall
                    )
                },
                colors = ButtonDefaults.buttonColors(containerColor = Blue),
                shape = RoundedCornerShape(8.dp)
            )
        }


    }) {


        if (apiResponse.vacancies.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(it).padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                if (apiResponse.offers.isNotEmpty() && !suitablePositions) {
                    item {

                        LazyRow(
                            modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {

                            items(apiResponse.offers) { item ->

                                OfferCard(item)

                            }
//
                            item {

                                Spacer(Modifier.size(8.dp))
                            }


                        }
                    }
                }



                if (apiResponse.vacancies.isNotEmpty()) {

                    if (suitablePositions) {
                        item {
                            Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)) {

                                Text("${apiResponse.vacancies.size.toString()} вакансий")
                                Spacer(modifier = Modifier.weight(1f))
                                Text(
                                    "По соответствию",
                                    color = Blue,
                                    style = MaterialTheme.typography.bodyMedium,

                                    )

                                Icon(
                                    vectorResource(Res.drawable.sort_icon),
                                    "",
                                    modifier = Modifier.size(24.dp),
                                    tint = Blue
                                )

                            }
                        }
                    } else {

                        item {
                            Row(modifier = Modifier.padding(horizontal = 16.dp)) {

                                Text(
                                    text = "Вакансии для вас",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }


                    }

                }

                items(if (suitablePositions) apiResponse.vacancies else positions) { vacancy ->

                    {
                        onItemClicked(vacancy)
                        viewModel.toggleFavorite(vacancy.id)
                    }

                    VacancyCard(viewModel = viewModel,
                        vacancy = vacancy,
                        onItemClicked = { onItemClicked(vacancy) },
                        onToggleFavoriteClicked = { onToggleFavoriteClicked(vacancy.id) })

                }

                item {
                    Spacer(Modifier.size(0.dp))
                }


            }
        }


    }

}

@Composable
fun CustomSearchBar() {

    val height = 48.dp

    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Card(
            modifier = Modifier.weight(1f).height(height),
            colors = CardDefaults.cardColors(containerColor = Grey2)
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    vectorResource(Res.drawable.search),
                    "",
                    modifier = Modifier.size(24.dp),
                    tint = Grey4
                )

                Spacer(Modifier.size(12.dp))


                Text(
                    "Должность по подходящим вакансиям",
                    color = Grey3,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

        }

        Spacer(Modifier.size(8.dp))

        Card(modifier = Modifier.size(height),
            colors = CardDefaults.cardColors(containerColor = Grey2),
            onClick = {}) {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Icon(vectorResource(Res.drawable.filter), null, modifier = Modifier.size(24.dp))
            }

        }

    }


}

@Composable
fun OfferCard(offer: Offer) {

    val uriHandler = LocalUriHandler.current

    Card(modifier = Modifier.size(width = 132.dp, height = 120.dp),
        colors = CardDefaults.cardColors(containerColor = Grey1),
        onClick = {
            uriHandler.openUri(offer.link)
        }) {

        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {

            if (offer.id.isNotEmpty()) {


                val icon = when (offer.id) {

                    "near_vacancies" -> {
                        vectorResource(Res.drawable.distance)
                    }

                    "level_up_resume" -> {
                        vectorResource(Res.drawable.level_up)
                    }

                    "temporary_job" -> {
                        vectorResource(Res.drawable.temp_job)
                    }

                    else -> {

                        Icons.Default.Error

                    }
                }

                val iconColor = when (offer.id) {

                    "near_vacancies" -> {
                        Blue
                    }

                    "level_up_resume" -> {
                        Green
                    }

                    "temporary_job" -> {
                        Green
                    }

                    else -> {
                        Green


                    }

                }

                val backgroundColor = when (offer.id) {

                    "near_vacancies" -> {
                        DarkBlue
                    }

                    "level_up_resume" -> {
                        DarkGreen
                    }

                    "temporary_job" -> {
                        DarkGreen
                    }

                    else -> {
                        DarkGreen


                    }

                }

                Box(
                    modifier = Modifier.size(32.dp).clip(CircleShape).background(backgroundColor),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(icon, null, tint = iconColor)
                }

                Spacer(Modifier.size(16.dp))
            }



            Text(
                text = offer.title,
                maxLines = if (true) 2 else 3,
                fontSize = 14.sp,
                lineHeight = 14.sp
            )

            if (offer.button.text.isNotEmpty()) {
                Text(offer.button.text)
            }


        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacancyCard(
    vacancy: Vacancy,
    onItemClicked: () -> Unit,
    onToggleFavoriteClicked: () -> Unit,
    viewModel: MainViewModel
) {

    val sheetState = rememberModalBottomSheetState()

    val scope = rememberCoroutineScope()

    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(sheetState = sheetState, onDismissRequest = {
            showBottomSheet = false
        }

        ) {

            Column(modifier = Modifier.padding(16.dp)) {


                Row(modifier = Modifier.fillMaxWidth()) {

                    Icon(vectorResource(Res.drawable.search), null)

                    Column {
                        Text("Резюме для отклика")
                        Text("UI/UX дизайнер")
                    }


                }
                Spacer(Modifier.size(24.dp))
                HorizontalDivider()
                Spacer(Modifier.size(40.dp))

                Text("Добавить сопроводительное")


                // Sheet content
                Button(onClick = {

                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }) {
                    Text("Откликнуться")
                }
            }


        }
    }


    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
        onClick = onItemClicked,
        colors = CardDefaults.cardColors(containerColor = Grey1)
    ) {

        Box(modifier = Modifier.fillMaxWidth()) {

            Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {

                Column {

                    Text(
                        text = "Сейчас просматривает ${vacancy.lookingNumber} человек",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.size(12.dp))

                    Text(vacancy.title, style = MaterialTheme.typography.titleSmall)
                    Spacer(Modifier.size(8.dp))
                    Text(vacancy.address.town)

                    Row(verticalAlignment = Alignment.CenterVertically) {

                        Text(vacancy.company)
                        Spacer(Modifier.size(8.dp))
                        Icon(
                            vectorResource(Res.drawable.checked),
                            null,
                            modifier = Modifier.size(16.dp),
                            tint = Grey3
                        )

                    }
                    Spacer(Modifier.size(4.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.ShoppingBag, "", modifier = Modifier.size(16.dp))
                        Text(vacancy.experience.previewText)
                    }

                    Spacer(Modifier.size(8.dp))
                    Text(
                        "Опубликовано ${vacancy.publishedDate}",
                        color = Grey3,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Spacer(Modifier.size(20.dp))


                Button(
                    onClick = {

                        showBottomSheet = true


                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(32.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        containerColor = Green, contentColor = White
//                    )
                ) {
                    Text("Oткликнуться")
                }
            }

//            FavoriteIcon(viewModel, onToggleFavoriteClicked, vacancy.id)

            val favs by viewModel.favsIds.collectAsState(initial = emptyList())


            val inFavorites = vacancy in favs

            IconButton(
                onClick = onToggleFavoriteClicked,
                modifier = Modifier.align(Alignment.TopEnd).padding(top = 4.dp, end = 4.dp)
            ) {

                if (inFavorites) {
                    Icon(
                        vectorResource(Res.drawable.heart_filled), "", tint = Blue
                    )
                } else {
                    Icon(
                        vectorResource(Res.drawable.heart_outlined), ""
                    )
                }


            }


        }

    }
}


//@Composable
//fun FavoriteIcon(viewModel: MainViewModel, onToggleFavoriteClicked: () -> Unit, id: String) {
//
//    val favs by viewModel.favsIds.collectAsState(emptySet())
//
//    val inFavorites = id in favs
//
//    IconButton(
//        onClick = onToggleFavoriteClicked, modifier = Modifier.padding(top = 4.dp, end = 4.dp)
//    ) {
//        Icon(
//            vectorResource(if (inFavorites) Res.drawable.heart_filled else Res.drawable.heart_outlined),
//            ""
//        )
//    }
//}

