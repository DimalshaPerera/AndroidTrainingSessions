package com.example.newswithfast.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newswithfast.models.data.NewsUiState
import com.example.newswithfast.models.repository.NewsRepository

class NewsViewModel : ViewModel() {
    private val repository = NewsRepository()

    private val _uiState = mutableStateOf(NewsUiState())
    val uiState: State<NewsUiState> = _uiState

    init {
        loadTopHeadlines()
    }

    fun loadTopHeadlines() {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        repository.getTopHeadlines(
            onSuccess = { articles ->
                _uiState.value = _uiState.value.copy(
                    articles = articles,
                    isLoading = false,
                    error = null
                )
            },
            onError = { error ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = error
                )
            }
        )
    }

    fun searchNews(query: String) {
        if (query.isBlank()) {
            loadTopHeadlines()
            return
        }

        _uiState.value = _uiState.value.copy(isLoading = true, error = null)

        repository.searchNews(
            query = query,
            onSuccess = { articles ->
                _uiState.value = _uiState.value.copy(
                    articles = articles,
                    isLoading = false,
                    error = null
                )
            },
            onError = { error ->
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = error
                )
            }
        )
    }

    fun retry() {
        loadTopHeadlines()
    }
}
