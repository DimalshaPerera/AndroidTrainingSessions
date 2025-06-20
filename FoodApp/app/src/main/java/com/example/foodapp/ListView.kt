package com.example.foodapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.data.FoodListItem

class ListView : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: FoodListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {

        recyclerView = findViewById(R.id.recyclerView)


        val foodList = createSampleFoodList()

        foodAdapter = FoodListAdapter(foodList)


        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListView)
            adapter = foodAdapter
        }
    }

    private fun createSampleFoodList(): List<FoodListItem> {
        return listOf(
            FoodListItem(
                id = 1,
                title = "Burger",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                price = "$12.99",
                imageResourceId = R.drawable.burger
            ),
            FoodListItem(
                id = 2,
                title = "Margherita Pizza",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                price = "$15.99",
                imageResourceId = R.drawable.burger
            ),
            FoodListItem(
                id = 3,
                title = "Caesar Salad",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                price = "$9.99",
                imageResourceId = R.drawable.burger
            ),
            FoodListItem(
                id = 4,
                title = "Chicken Wings",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                price = "$11.99",
                imageResourceId = R.drawable.burger
            )
        )
    }
}