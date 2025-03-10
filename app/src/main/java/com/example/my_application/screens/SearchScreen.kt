package com.example.my_application.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf(SearchFilter.TRACKS) }

    Scaffold(
        topBar = {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { /* TODO: Выполнить поиск */ },
                active = false,
                onActiveChange = {},
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Поиск музыки...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Поиск") }
            ) {}
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            // Фильтры поиска
            FilterChips(selectedFilter) { selectedFilter = it }
            
            // Результаты поиска
            SearchResults(searchQuery, selectedFilter)
        }
    }
}

@Composable
fun FilterChips(
    selectedFilter: SearchFilter,
    onFilterSelected: (SearchFilter) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedFilter.ordinal,
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        SearchFilter.values().forEach { filter ->
            Tab(
                selected = selectedFilter == filter,
                onClick = { onFilterSelected(filter) },
                text = { Text(filter.title) }
            )
        }
    }
}

@Composable
fun SearchResults(query: String, filter: SearchFilter) {
    if (query.isEmpty()) {
        EmptySearchState()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(getSampleResults(filter)) { result ->
                SearchResultItem(result)
            }
        }
    }
}

@Composable
fun EmptySearchState() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Введите запрос для поиска",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchResultItem(result: SearchResult) {
    ListItem(
        headlineContent = { Text(result.title) },
        supportingContent = { Text(result.subtitle) },
        trailingContent = {
            IconButton(onClick = { /* TODO: Добавить в плейлист */ }) {
                Icon(Icons.Default.Add, contentDescription = "Добавить")
            }
        }
    )
}

enum class SearchFilter(val title: String) {
    TRACKS("Треки"),
    ALBUMS("Альбомы"),
    ARTISTS("Исполнители"),
    PLAYLISTS("Плейлисты")
}

data class SearchResult(
    val title: String,
    val subtitle: String,
    val type: SearchFilter
)

fun getSampleResults(filter: SearchFilter): List<SearchResult> {
    return when (filter) {
        SearchFilter.TRACKS -> listOf(
            SearchResult("Shape of You", "Ed Sheeran", SearchFilter.TRACKS),
            SearchResult("Blinding Lights", "The Weeknd", SearchFilter.TRACKS)
        )
        SearchFilter.ALBUMS -> listOf(
            SearchResult("÷ (Divide)", "Ed Sheeran • 2017", SearchFilter.ALBUMS),
            SearchResult("After Hours", "The Weeknd • 2020", SearchFilter.ALBUMS)
        )
        SearchFilter.ARTISTS -> listOf(
            SearchResult("Ed Sheeran", "Исполнитель", SearchFilter.ARTISTS),
            SearchResult("The Weeknd", "Исполнитель", SearchFilter.ARTISTS)
        )
        SearchFilter.PLAYLISTS -> listOf(
            SearchResult("Топ 50 - Мир", "Плейлист • Spotify", SearchFilter.PLAYLISTS),
            SearchResult("Поп-музыка 2024", "Плейлист • User", SearchFilter.PLAYLISTS)
        )
    }
} 