package com.example.my_application.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistsScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Мои плейлисты", "Подписки")

    Scaffold(
        topBar = {
            Column {
                SmallTopAppBar(
                    title = { Text("Плейлисты") }
                )
                TabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) }
                        )
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Создать новый плейлист */ }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Создать плейлист")
            }
        }
    ) { paddingValues ->
        when (selectedTab) {
            0 -> MyPlaylists(paddingValues)
            1 -> SubscribedPlaylists(paddingValues)
        }
    }
}

@Composable
fun MyPlaylists(paddingValues: PaddingValues) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize()
    ) {
        items(sampleMyPlaylists) { playlist ->
            PlaylistGridItem(
                playlist = playlist,
                showEditButton = true
            )
        }
    }
}

@Composable
fun SubscribedPlaylists(paddingValues: PaddingValues) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 160.dp),
        contentPadding = paddingValues,
        modifier = Modifier.fillMaxSize()
    ) {
        items(sampleSubscribedPlaylists) { playlist ->
            PlaylistGridItem(
                playlist = playlist,
                showEditButton = false
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaylistGridItem(
    playlist: PlaylistDetails,
    showEditButton: Boolean
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        onClick = { /* TODO: Открыть плейлист */ }
    ) {
        Column {
            Box {
                AsyncImage(
                    model = playlist.imageUrl,
                    contentDescription = playlist.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    contentScale = ContentScale.Crop
                )
                if (showEditButton) {
                    IconButton(
                        onClick = { /* TODO: Редактировать плейлист */ },
                        modifier = Modifier.align(androidx.compose.ui.Alignment.TopEnd)
                    ) {
                        Icon(
                            Icons.Default.Edit,
                            contentDescription = "Редактировать",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = playlist.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "${playlist.tracksCount} треков",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

data class PlaylistDetails(
    val name: String,
    val imageUrl: String,
    val tracksCount: Int,
    val isOwner: Boolean
)

val sampleMyPlaylists = listOf(
    PlaylistDetails(
        "Мой плейлист #1",
        "https://picsum.photos/300",
        15,
        true
    ),
    PlaylistDetails(
        "Любимые треки",
        "https://picsum.photos/301",
        42,
        true
    ),
    PlaylistDetails(
        "Для тренировки",
        "https://picsum.photos/302",
        28,
        true
    ),
    PlaylistDetails(
        "Для работы",
        "https://picsum.photos/303",
        35,
        true
    )
)

val sampleSubscribedPlaylists = listOf(
    PlaylistDetails(
        "Топ 50 - Россия",
        "https://picsum.photos/304",
        50,
        false
    ),
    PlaylistDetails(
        "Новые хиты",
        "https://picsum.photos/305",
        100,
        false
    ),
    PlaylistDetails(
        "Инди микс",
        "https://picsum.photos/306",
        75,
        false
    ),
    PlaylistDetails(
        "Классика рока",
        "https://picsum.photos/307",
        120,
        false
    )
) 