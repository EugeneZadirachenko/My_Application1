package com.example.myapplication.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.search.model.SearchItem
import com.example.myapplication.search.domain.SearchUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

sealed class SearchState {
    object Initial : SearchState()
    object Loading : SearchState()
    data class Success(val items: List<SearchItem>) : SearchState()
    data class Error(val message: String) : SearchState()
}

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow<SearchState>(SearchState.Initial)
    val searchState: StateFlow<SearchState> = _searchState

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private var searchJob: Job? = null

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        searchJob?.cancel()
        
        if (query.isEmpty()) {
            _searchState.value = SearchState.Initial
            return
        }

        searchJob = viewModelScope.launch {
            delay(300) // Debounce delay
            performSearch(query)
        }
    }

    private fun performSearch(query: String) {
        viewModelScope.launch {
            _searchState.value = SearchState.Loading
            try {
                searchUseCase(query)
                    .catch { e ->
                        _searchState.value = SearchState.Error(e.message ?: "Неизвестная ошибка")
                    }
                    .collect { items ->
                        _searchState.value = SearchState.Success(items)
                    }
            } catch (e: Exception) {
                _searchState.value = SearchState.Error(e.message ?: "Неизвестная ошибка")
            }
        }
    }
} 