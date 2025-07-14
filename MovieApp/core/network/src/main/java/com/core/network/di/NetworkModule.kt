package com.core.network.di

import com.core.network.ApiService
import com.core.network.dataproviders.MovieDataProviders
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {


    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create()).build().create(ApiService::class.java)
    }
//converter factory maps the json to the model cls


    @Provides
    @Singleton
    fun provideMovieDataProvider(apiService: ApiService):MovieDataProviders{
        return MovieDataProviders(apiService)
    }
}