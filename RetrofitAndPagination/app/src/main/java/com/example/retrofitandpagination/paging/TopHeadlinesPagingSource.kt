package com.example.retrofitandpagination.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.retrofitandpagination.models.NewsApiService
import com.example.retrofitandpagination.models.data.Article
import kotlinx.coroutines.delay
//A paging source defines how to load data page by page.


class TopHeadlinesPagingSource(
    private val apiService: NewsApiService,
    private val apiKey: String,
    private val country: String = "us"
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1

            delay(300)

            val response = apiService.getTopHeadlines(
                country = country,
                page = page,
                pageSize = params.loadSize,
                apiKey = apiKey
            )

            if (response.isSuccessful) {
                val newsResponse = response.body()!!
                val articles = newsResponse.articles

                LoadResult.Page(
                    data = articles,
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (articles.isEmpty()) null else page + 1
                )
            } else {
                LoadResult.Error(Exception("API Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}