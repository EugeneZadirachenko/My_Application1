package com.example.myapplication.search.repository

import com.example.myapplication.search.model.SearchItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SearchRepository {
    fun searchItems(query: String): Flow<List<SearchItem>>
}

class FakeSearchRepository : SearchRepository {
    private val fakeItems = listOf(
        SearchItem("1", "Первый элемент", "Описание первого элемента"),
        SearchItem("2", "Второй элемент", "Описание второго элемента"),
        SearchItem("3", "Третий элемент", "Описание третьего элемента")
    )

    override fun searchItems(query: String): Flow<List<SearchItem>> = flow {
        // Имитация задержки сети
        kotlinx.coroutines.delay(1000)
        emit(if (query.isBlank()) {
            fakeItems
        } else {
            fakeItems.filter { 
                it.title.contains(query, ignoreCase = true) || 
                it.description.contains(query, ignoreCase = true)
            }
        })
    }
} 