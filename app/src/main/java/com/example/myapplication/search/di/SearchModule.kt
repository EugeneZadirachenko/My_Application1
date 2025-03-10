package com.example.myapplication.search.di

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myapplication.search.repository.FakeSearchRepository
import com.example.myapplication.search.repository.SearchRepository
import com.example.myapplication.search.domain.SearchUseCase
import com.example.myapplication.search.viewmodel.SearchViewModel

object SearchModule {
    private val repository: SearchRepository by lazy { FakeSearchRepository() }
    private val searchUseCase: SearchUseCase by lazy { SearchUseCase(repository) }

    val Factory = viewModelFactory {
        initializer {
            SearchViewModel(searchUseCase)
        }
    }
} 