package com.example.my_application.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Профиль") },
                actions = {
                    IconButton(onClick = { /* TODO: Открыть настройки */ }) {
                        Icon(Icons.Default.Settings, contentDescription = "Настройки")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                ProfileHeader()
            }
            item {
                StatisticsSection()
            }
            item {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }
            item {
                Text(
                    text = "Музыкальный дневник",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }
            items(sampleDiaryEntries) { entry ->
                DiaryEntryItem(entry)
            }
        }
    }
}

@Composable
fun ProfileHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            AsyncImage(
                model = "https://picsum.photos/200",
                contentDescription = "Фото профиля",
                modifier = Modifier
                    .size(120.dp)
                    .clip(MaterialTheme.shapes.medium),
                contentScale = ContentScale.Crop
            )
            IconButton(
                onClick = { /* TODO: Изменить фото */ },
                modifier = Modifier.align(Alignment.BottomEnd)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Изменить фото")
            }
        }
        Text(
            text = "Евгений",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = "@eugene",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Button(
            onClick = { /* TODO: Редактировать профиль */ },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Icon(Icons.Default.Edit, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Редактировать профиль")
        }
    }
}

@Composable
fun StatisticsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        StatisticItem("Прослушано", "1,234")
        StatisticItem("Плейлисты", "15")
        StatisticItem("Подписчики", "256")
    }
}

@Composable
fun StatisticItem(label: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryEntryItem(entry: DiaryEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = entry.trackImageUrl,
                        contentDescription = null,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(MaterialTheme.shapes.small),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier.padding(start = 12.dp)
                    ) {
                        Text(
                            text = entry.trackName,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(
                            text = entry.artist,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
                Text(
                    text = entry.date,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = entry.note,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { /* TODO: Редактировать запись */ }) {
                    Icon(Icons.Default.Edit, contentDescription = "Редактировать")
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Редактировать")
                }
            }
        }
    }
}

data class DiaryEntry(
    val trackName: String,
    val artist: String,
    val trackImageUrl: String,
    val note: String,
    val date: String
)

val sampleDiaryEntries = listOf(
    DiaryEntry(
        "Shape of You",
        "Ed Sheeran",
        "https://picsum.photos/600",
        "Отличный трек для тренировки! Энергичный ритм и запоминающаяся мелодия.",
        "Сегодня"
    ),
    DiaryEntry(
        "Blinding Lights",
        "The Weeknd",
        "https://picsum.photos/601",
        "Этот трек напоминает мне о летних вечерах. Синтвейв настроение просто потрясающее!",
        "Вчера"
    ),
    DiaryEntry(
        "Stay",
        "The Kid LAROI & Justin Bieber",
        "https://picsum.photos/602",
        "Идеальное сочетание голосов. Слушаю на повторе весь день.",
        "2 дня назад"
    )
) 