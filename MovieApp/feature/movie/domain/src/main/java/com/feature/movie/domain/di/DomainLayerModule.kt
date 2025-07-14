package com.feature.movie.domain.di

import com.feature.movie.domain.repo.MovieRepository
import com.feature.movie.domain.use_cases.GetMovieListUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DomainLayerModule {

    @Singleton
    @Provides
    fun provideGetMovieListUseCase(movieRepository: MovieRepository):GetMovieListUseCase{
        return GetMovieListUseCase(movieRepository)
    }


}