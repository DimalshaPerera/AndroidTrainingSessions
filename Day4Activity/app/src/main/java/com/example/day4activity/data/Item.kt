package com.example.day4activity.data

import com.example.day4activity.R

data class Item(
    val title: String,
    val description: String,
    val userAvatars: List<Int> = listOf(R.drawable.user, R.drawable.user, R.drawable.user)
)
