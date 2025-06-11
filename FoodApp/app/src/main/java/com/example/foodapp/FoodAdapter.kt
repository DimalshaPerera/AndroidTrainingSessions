
package com.example.foodapp
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodapp.data.FoodItem
import com.example.foodapp.databinding.FoodItemBinding

class FoodAdapter(
    private val foodItems: List<FoodItem>,
    private val onItemClick: (FoodItem) -> Unit = {}
) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    class FoodViewHolder(private val binding: FoodItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(foodItem: FoodItem, onItemClick: (FoodItem) -> Unit) {
            binding.foodName.text = foodItem.name
            binding.foodPrice.text = foodItem.price
            binding.foodImage.setImageResource(foodItem.imageResource)
            try {
                binding.itemContainer.setBackgroundColor(Color.parseColor(foodItem.backgroundColor))
            } catch (e: Exception) {
                binding.itemContainer.setBackgroundColor(Color.parseColor("#90EE90"))
            }


            binding.root.setOnClickListener {
                onItemClick(foodItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val binding = FoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.bind(foodItems[position], onItemClick)
    }

    override fun getItemCount(): Int = foodItems.size
}