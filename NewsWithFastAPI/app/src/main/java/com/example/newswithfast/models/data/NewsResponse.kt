package com.example.newswithfast.models.data

data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)
