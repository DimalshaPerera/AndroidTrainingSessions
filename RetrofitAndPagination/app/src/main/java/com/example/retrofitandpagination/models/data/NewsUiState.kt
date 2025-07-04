package com.example.retrofitandpagination.models.data

data class NewsUiState(
    val isSearching: Boolean = false,
    val searchQuery: String = "",
    val error: String? = null
)