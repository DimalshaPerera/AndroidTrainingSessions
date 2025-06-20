package com.example.foodapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.data.FoodListItem

class FoodListAdapter(
    private var foodList: List<FoodListItem>
) : RecyclerView.Adapter<FoodListAdapter.FoodViewHolder>() {


    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemImage: ImageView = itemView.findViewById(R.id.itemImage)
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
        val price: TextView = itemView.findViewById(R.id.priceTe)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_item_layout, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val foodItem = foodList[position]

        holder.title.text = foodItem.title
        holder.description.text = foodItem.description
        holder.price.text = foodItem.price
        holder.itemImage.setImageResource(foodItem.imageResourceId)
    }

    override fun getItemCount(): Int = foodList.size


    fun updateList(newList: List<FoodListItem>) {
        foodList = newList
        notifyDataSetChanged()
    }
}