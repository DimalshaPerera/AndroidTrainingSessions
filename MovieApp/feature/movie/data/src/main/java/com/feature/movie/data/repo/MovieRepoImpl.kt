package com.feature.movie.data.repo


import com.core.network.dataproviders.MovieDataProviders
import com.feature.movie.data.mapper.toDomainMovieList
import com.feature.movie.domain.model.Movie
import com.feature.movie.domain.repo.MovieRepository
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepoImpl @Inject constructor(private val movieDataProviders:MovieDataProviders) : MovieRepository {
    override suspend fun getMovieList(apiKey: String, q: String): List<Movie> {
        return movieDataProviders.getMovieLIst(apiKey,q).toDomainMovieList()
    }
}