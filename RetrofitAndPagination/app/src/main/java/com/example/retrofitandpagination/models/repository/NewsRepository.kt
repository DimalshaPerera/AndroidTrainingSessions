package com.example.retrofitandpagination.models.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.retrofitandpagination.models.NetworkModule
import com.example.retrofitandpagination.models.data.Article
import com.example.retrofitandpagination.paging.SearchNewsPagingSource
import com.example.retrofitandpagination.paging.TopHeadlinesPagingSource
import kotlinx.coroutines.flow.Flow

class NewsRepository {
    companion object {
        private const val API_KEY = "1f75a4823a724c67b9ad7a1114b615c5"
        private const val PAGE_SIZE = 20
    }

    private val apiService = NetworkModule.newsApiService

    fun getTopHeadlines(country: String = "us"): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                TopHeadlinesPagingSource(
                    apiService = apiService,
                    apiKey = API_KEY,
                    country = country
                )
            }
        ).flow
    }

    fun searchNews(query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                initialLoadSize = PAGE_SIZE
            ),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    apiService = apiService,
                    apiKey = API_KEY,
                    query = query
                )
            }
        ).flow
    }
}