package com.example.newswithfast.models.repository

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.newswithfast.models.data.Article
import com.example.newswithfast.models.data.NewsResponse

class NewsRepository {
    // kinda similar to java static
    companion object {
        private const val API_KEY = "1f75a4823a724c67b9ad7a1114b615c5"
        private const val BASE_URL =  "https://newsapi.org/v2/"
    }

    fun getTopHeadlines(
        country: String = "us",
        onSuccess: (List<Article>) -> Unit,
        onError: (String) -> Unit
    ) {
        //GET RE
        AndroidNetworking.get("${BASE_URL}top-headlines")
            .addQueryParameter("country", country)
            .addQueryParameter("apiKey", API_KEY)
            .setPriority(Priority.HIGH)
            .build()
            .getAsObject(NewsResponse::class.java, object : ParsedRequestListener<NewsResponse> {
                override fun onResponse(response: NewsResponse) {
                    onSuccess(response.articles)
                }

                override fun onError(anError: ANError) {
                    onError(anError.errorDetail ?: "Unknown error occurred")
                }
            })
    }

    fun searchNews(
        query: String,
        onSuccess: (List<Article>) -> Unit,
        onError: (String) -> Unit
    ) {

        //GET REQ
        AndroidNetworking.get("${BASE_URL}everything")
            .addQueryParameter("q", query)
            .addQueryParameter("apiKey", API_KEY)
            .addQueryParameter("sortBy", "publishedAt")
            .setPriority(Priority.MEDIUM)
            .build()
            //here this converts json -> kotlin auto
            .getAsObject(NewsResponse::class.java, object : ParsedRequestListener<NewsResponse> {
                override fun onResponse(response: NewsResponse) {
                    onSuccess(response.articles)
                }

                override fun onError(anError: ANError) {
                    onError(anError.errorDetail ?: "Unknown error occurred")
                }
            })
    }
}