package com.example.retrofitandpagination.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.retrofitandpagination.models.data.Article
import com.example.retrofitandpagination.models.data.NewsUiState
import com.example.retrofitandpagination.models.repository.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest


class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()

    private val _uiState = mutableStateOf(NewsUiState())
    val uiState: State<NewsUiState> = _uiState

    private val _searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    val articles: Flow<PagingData<Article>> = _searchQuery
        .flatMapLatest { query ->
            if (query.isBlank()) {
                repository.getTopHeadlines()
            } else {
                repository.searchNews(query)
            }
        }
        .cachedIn(viewModelScope)

    fun searchNews(query: String) {
        _uiState.value = _uiState.value.copy(
            searchQuery = query,
            isSearching = query.isNotBlank()
        )
        _searchQuery.value = query
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}