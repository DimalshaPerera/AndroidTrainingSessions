package com.example.fastapi.models.data

data class NewsUiState(
    val getResponse: String = "No GET request made yet",
    val postResponse: String = "No POST request made yet",
    val isLoading: Boolean = false,
    val postTitle: String = "",
    val postBody: String = ""
)
