package com.example.foodapp.data


data class FoodItem(
    val id: Int,
    val name: String,
    val price: String,
    val imageResource: Int,
    val backgroundColor: String = "#90EE90"
)