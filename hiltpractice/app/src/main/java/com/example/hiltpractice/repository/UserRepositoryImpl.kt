package com.example.hiltpractice.repository

import com.example.hiltpractice.data.UserRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor() : UserRepository {
    override fun getUserName(): String {
        return "Dimalsha"
    }
}