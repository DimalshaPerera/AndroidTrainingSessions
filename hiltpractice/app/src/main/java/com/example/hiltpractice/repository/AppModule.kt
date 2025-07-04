package com.example.hiltpractice.repository

import com.example.hiltpractice.data.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository {
        return UserRepositoryImpl()
    }
}
//abstract class RepoModule {
//    @Binds
//    @Singleton
//    abstract fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
//}