package com.example.foodapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.foodapp.data.FoodItem
import com.example.foodapp.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var foodAdapter: FoodAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        val foodItems = listOf(
            FoodItem(1, "Pepperoni Pizza", "$16.00", R.drawable.pizza2, "#FFE4B5"),
            FoodItem(2, "Bolognese Pasta", "$16.00", R.drawable.pizza2, "#FFB6C1"),
            FoodItem(3, "Margherita Pizza", "$16.00", R.drawable.pizza2, "#98FB98"),
            FoodItem(4, "Margherita Pizza", "$16.00", R.drawable.pizza2, "#98FB98"),
            FoodItem(5, "Margherita Pizza", "$16.00", R.drawable.pizza2, "#98FB98"),
        )
        foodAdapter = FoodAdapter(foodItems) { foodItem ->
            Toast.makeText(this, "Clicked: ${foodItem.name}", Toast.LENGTH_SHORT).show()
        }

        binding.foodRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@Home)
            adapter = foodAdapter
        }
    }



}