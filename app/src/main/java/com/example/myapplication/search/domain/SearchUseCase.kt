package com.example.myapplication.search.domain

import com.example.myapplication.search.model.SearchItem
import com.example.myapplication.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchUseCase(private val repository: SearchRepository) {
    suspend operator fun invoke(query: String): Flow<List<SearchItem>> {
        return repository.searchItems(query)
            .map { items ->
                // Пример бизнес-логики: сортировка результатов
                items.sortedBy { it.title }
            }
    }
} 