package com.example.myapplication.search.model

data class SearchItem(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String? = null
) 