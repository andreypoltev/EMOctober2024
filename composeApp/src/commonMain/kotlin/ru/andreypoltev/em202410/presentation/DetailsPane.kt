package ru.andreypoltev.em202410.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import emoctober2024.composeapp.generated.resources.Res
import emoctober2024.composeapp.generated.resources.checked
import org.jetbrains.compose.resources.vectorResource
import ru.andreypoltev.em202410.data.model.Vacancy
import ru.andreypoltev.em202410.theme.DarkGreen
import ru.andreypoltev.em202410.theme.Grey0
import ru.andreypoltev.em202410.theme.Grey1
import ru.andreypoltev.em202410.theme.Grey3
import ru.andreypoltev.em202410.theme.White

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
        }, colors = TopAppBarDefaults.topAppBarColors(
            navigationIconContentColor = White, actionIconContentColor = White
        )
        )
    }, containerColor = Grey0) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxWidth().padding(paddingValues).padding(horizontal = 16.dp)
                .verticalScroll(
                    rememberScrollState()
                )
        ) {
            Spacer(Modifier.size(24.dp))

            Text(text = vacancy.title, style = MaterialTheme.typography.titleLarge)

            Spacer(Modifier.size(16.dp))

            Text(text = vacancy.salary.full, style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.size(16.dp))

            Text(
                text = "Требуемый опыт: ${vacancy.experience.text}",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.size(8.dp))

            Text(
                text = vacancy.schedules.joinToString()
                    .replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() },
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.size(24.dp))

            Row(modifier = Modifier.fillMaxWidth()) {


                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = DarkGreen)
                ) {

                    Text(
                        "${vacancy.appliedNumber} человек уже откликнулись",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )

                }

                Spacer(Modifier.size(8.dp))

                Card(
                    modifier = Modifier.weight(1f),
                    colors = CardDefaults.cardColors(containerColor = DarkGreen)
                ) {

                    Text(
                        "${vacancy.lookingNumber} человека сейчас смотрят",
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)
                    )

                }


            }

            Spacer(Modifier.size(24.dp))

//            Box(modifier = Modifier.fillMaxWidth().height(110.dp).background(DarkGreen))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Grey1)
            ) {

                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 12.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = vacancy.company, style = MaterialTheme.typography.titleSmall
                        )

                        Spacer(modifier = Modifier.size(8.dp))


                        Icon(vectorResource(Res.drawable.checked), null, tint = Grey3)

                    }



                    Spacer(modifier = Modifier.size(8.dp))

                    Box(
                        modifier = Modifier.fillMaxWidth().height(58.dp).clip(
                            RoundedCornerShape(20.dp)
                        ).background(color = DarkGreen)
                    )

                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = "${vacancy.address.town}, ${vacancy.address.street}, ${vacancy.address.house}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }


            }

            Spacer(Modifier.size(16.dp))

            Text(text = vacancy.description, style = MaterialTheme.typography.bodyMedium)

            Spacer(Modifier.size(16.dp))

            Text("Ваши задачи", style = MaterialTheme.typography.titleMedium)

            Spacer(Modifier.size(8.dp))

            Text(text = vacancy.responsibilities, style = MaterialTheme.typography.bodyMedium)






        }

    }


}