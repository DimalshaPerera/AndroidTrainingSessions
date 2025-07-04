package com.example.retrofitandpagination.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.retrofitandpagination.models.NewsApiService
import com.example.retrofitandpagination.models.data.Article
import kotlinx.coroutines.delay

class SearchNewsPagingSource(
    private val apiService: NewsApiService,
    private val apiKey: String,
    private val query: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val page = params.key ?: 1


            delay(300)

            val response = apiService.searchNews(
                query = query,
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


    // Determines the key (page number) to reload when refreshing the list.
// It finds the page closest to the last accessed scroll position and returns the appropriate page number.
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}