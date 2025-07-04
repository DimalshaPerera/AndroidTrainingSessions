package com.example.puredependency.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.puredependency.data.repository.UserRepository

class UserViewModel(private val userRepository: UserRepository) :ViewModel() {
    var userName by mutableStateOf("")
    private set

    fun loadUser(){
        userName=userRepository.getUserName()
    }

}
