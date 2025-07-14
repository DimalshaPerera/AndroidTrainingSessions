package com.feature.movie.data.di

import com.core.network.dataproviders.MovieDataProviders
import com.feature.movie.data.repo.MovieRepoImpl
import com.feature.movie.domain.repo.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataLayerModule {

//    @Provides
//    @Singleton
//    fun provideMovieRepo(movieDataProviders: MovieDataProviders): MovieRepository {
//        return MovieRepoImpl(movieDataProviders)
//    }
@Provides
fun provideMovieRepository(
    apiService: MovieDataProviders
): MovieRepository {
    return MovieRepoImpl(apiService)
}

}