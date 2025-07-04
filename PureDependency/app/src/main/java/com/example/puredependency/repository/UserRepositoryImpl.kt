package com.example.puredependency.repository

import com.example.puredependency.data.repository.UserRepository

class UserRepositoryImpl :UserRepository {
    override fun getUserName(): String {
        return "Dimalsha "
    }
}