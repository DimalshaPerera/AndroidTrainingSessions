package com.example.fastapi.viewmodels

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.fastapi.models.data.NewsUiState
import org.json.JSONObject

class NewsViewModel(application: Application) : AndroidViewModel(application) {

    private var _uiState = mutableStateOf(NewsUiState())
    val uiState: State<NewsUiState> = _uiState


    private val NEWS_API_KEY = "1f75a4823a724c67b9ad7a1114b615c5"

    init {
        // Initializingg Android Networking part
        AndroidNetworking.initialize(application.applicationContext)
    }

    fun updatePostTitle(title: String) {
        _uiState.value = _uiState.value.copy(postTitle = title)
    }

    fun updatePostBody(body: String) {
        _uiState.value = _uiState.value.copy(postBody = body)
    }

    fun fetchTopNews() {
        _uiState.value = _uiState.value.copy(isLoading = true)


        AndroidNetworking.get("https://newsapi.org/v2/top-headlines")
            .addQueryParameter("country", "us")
            .addQueryParameter("pageSize", "5")
            .addQueryParameter("apiKey", NEWS_API_KEY)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        val articles = response.getJSONArray("articles")
                        val totalResults = response.getInt("totalResults")

                        if (articles.length() > 0) {
                            val firstArticle = articles.getJSONObject(0)
                            val title = firstArticle.getString("title")
                            val description = firstArticle.optString("description", "No description")
                            val source = firstArticle.getJSONObject("source").getString("name")

                            val responseText = "News Success!\n" +
                                    "Total Articles: $totalResults\n" +
                                    "First Article:\n" +
                                    "Title: $title\n" +
                                    "Source: $source\n" +
                                    "Description: ${description.take(100)}..."

                            _uiState.value = _uiState.value.copy(
                                getResponse = responseText,
                                isLoading = false
                            )
                        } else {
                            _uiState.value = _uiState.value.copy(
                                getResponse = "Response received but no articles found",
                                isLoading = false
                            )
                        }
                    } catch (e: Exception) {
                        _uiState.value = _uiState.value.copy(
                            getResponse = " Response received but parsing failed: ${e.message}",
                            isLoading = false
                        )
                    }
                }

                override fun onError(anError: ANError) {
                    val errorMessage = if (anError.errorCode == 401) {
                        "API Key Error: Please add your NewsAPI key to the code"
                    } else {
                        " Error: ${anError.errorBody ?: anError.errorDetail}"
                    }

                    _uiState.value = _uiState.value.copy(
                        getResponse = errorMessage,
                        isLoading = false
                    )
                }
            })
    }

    fun makePostRequest() {
        val currentState = _uiState.value
        if (currentState.postTitle.isBlank() || currentState.postBody.isBlank()) {
            return
        }

        _uiState.value = currentState.copy(isLoading = true)

        val jsonObject = JSONObject().apply {
            put("title", currentState.postTitle)
            put("body", currentState.postBody)
            put("userId", 1)
        }


        AndroidNetworking.post("https://jsonplaceholder.typicode.com/posts")
            .addJSONObjectBody(jsonObject)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    try {
                        val id = response.getInt("id")
                        val title = response.getString("title")
                        val body = response.getString("body")

                        val responseText = "POST Success!\n" +
                                "ID: $id\n" +
                                "Title: $title\n" +
                                "Body: $body"

                        _uiState.value = _uiState.value.copy(
                            postResponse = responseText,
                            isLoading = false
                        )
                    } catch (e: Exception) {
                        _uiState.value = _uiState.value.copy(
                            postResponse = " POST response received but parsing failed: ${e.message}",
                            isLoading = false
                        )
                    }
                }

                override fun onError(anError: ANError) {
                    _uiState.value = _uiState.value.copy(
                        postResponse = " POST Error: ${anError.errorDetail}",
                        isLoading = false
                    )
                }
            })
    }

    fun clearResponses() {
        _uiState.value = _uiState.value.copy(
            getResponse = "No GET request made yet",
            postResponse = "No POST request made yet"
        )
    }
}