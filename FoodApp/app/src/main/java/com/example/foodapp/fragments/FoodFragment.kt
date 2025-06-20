package com.example.foodapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.FoodListAdapter
import com.example.foodapp.R
import com.example.foodapp.data.FoodListItem


/**
 * A simple [Fragment] subclass.
 * Use the [FoodFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class FoodFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var foodAdapter: FoodListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView(view)
    }

    private fun setupRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.recyclerView)

        val foodList = createSampleFoodList()
        foodAdapter = FoodListAdapter(foodList)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
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
            ),
            FoodListItem(
                id = 5,
                title = "Pasta Carbonara",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                price = "$13.99",
                imageResourceId = R.drawable.burger
            ),
            FoodListItem(
                id = 6,
                title = "Fish and Chips",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit",
                price = "$14.99",
                imageResourceId = R.drawable.burger
            )
        )
    }
}