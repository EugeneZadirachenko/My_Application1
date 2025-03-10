package com.example.my_application.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Музыка") },
                actions = {
                    IconButton(onClick = { /* TODO: Обновить рекомендации */ }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Обновить")
                    }
                    IconButton(onClick = { /* TODO: Открыть уведомления */ }) {
                        Icon(Icons.Default.Notifications, contentDescription = "Уведомления")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Баннер рекомендуемого альбома
            FeaturedAlbumBanner()
            
            // Секция "Для вас"
            PersonalizedSection()
            
            // Секция "Популярное"
            PopularSection()
        }
    }
}

@Composable
fun FeaturedAlbumBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(16.dp)
    ) {
        Box {
            AsyncImage(
                model = "https://picsum.photos/800/400", // Placeholder image
                contentDescription = "Featured Album",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Button(
                onClick = { /* TODO: Воспроизвести альбом */ },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Text("Слушать")
            }
        }
    }
}

@Composable
fun PersonalizedSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Для вас",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(samplePlaylists) { playlist ->
                PlaylistCard(playlist)
            }
        }
    }
}

@Composable
fun PopularSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Популярное",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            sampleTracks.forEach { track ->
                TrackItem(track)
            }
        }
    }
}

@Composable
fun PlaylistCard(playlist: Playlist) {
    Card(
        modifier = Modifier
            .width(160.dp)
            .padding(4.dp)
    ) {
        Column {
            AsyncImage(
                model = playlist.imageUrl,
                contentDescription = playlist.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = playlist.name,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun TrackItem(track: Track) {
    ListItem(
        headlineContent = { Text(track.name) },
        supportingContent = { Text(track.artist) },
        leadingContent = {
            AsyncImage(
                model = track.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
    )
}

// Модели данных
data class Playlist(
    val name: String,
    val imageUrl: String
)

data class Track(
    val name: String,
    val artist: String,
    val imageUrl: String
)

// Примеры данных
val samplePlaylists = listOf(
    Playlist("Твой микс", "https://picsum.photos/200"),
    Playlist("Новые релизы", "https://picsum.photos/201"),
    Playlist("Хиты 2024", "https://picsum.photos/202"),
    Playlist("Любимое", "https://picsum.photos/203")
)

val sampleTracks = listOf(
    Track("Shape of You", "Ed Sheeran", "https://picsum.photos/204"),
    Track("Blinding Lights", "The Weeknd", "https://picsum.photos/205"),
    Track("Dance Monkey", "Tones and I", "https://picsum.photos/206"),
    Track("Stay", "The Kid LAROI & Justin Bieber", "https://picsum.photos/207")
) 