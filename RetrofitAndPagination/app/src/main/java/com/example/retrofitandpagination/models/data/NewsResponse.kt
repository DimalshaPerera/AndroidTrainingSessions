package com.example.retrofitandpagination.models.data

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
