package com.core.network

import com.core.network.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//https://api.themoviedb.org/3/search/movie?api_key=0c2f7fcf948fc1d73c3b45af7cd1cf8d&query=Harry
@GET("3/search/movie")
suspend fun getMovieList(
    @Query("api_key") apiKey:String,
    @Query("query") q:String
): MovieListResponse



}
