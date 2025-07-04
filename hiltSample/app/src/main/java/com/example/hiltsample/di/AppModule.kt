package com.example.hiltsample.di

import com.example.hiltsample.data.model.CryptocurrencyRepository
import com.example.hiltsample.repository.CryptocurrencyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindCryptocurrencyRepository(
        cryptocurrencyRepositoryImpl: CryptocurrencyRepositoryImpl
    ): CryptocurrencyRepository
}

