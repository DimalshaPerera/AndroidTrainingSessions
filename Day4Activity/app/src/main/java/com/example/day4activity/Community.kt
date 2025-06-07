package com.example.day4activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.day4activity.data.Item
import com.example.day4activity.databinding.ActivityCommunityBinding

class Community : AppCompatActivity() {

    private lateinit var binding: ActivityCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        binding.backbtn.setOnClickListener {
            val intent = Intent(this, Profile::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupRecyclerView() {
        val items = listOf(
            Item(
                title = "How to Start investing in uStock",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam sollicitudin mollis eros quis dapibus. Proin non gravida eros, vel vehicula purus."
            ),
            Item(
                title = "How to predict candlestick",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam sollicitudin mollis eros quis dapibus. Proin non gravida eros, vel vehicula purus."
            ),
            Item(
                title = "Best Trading Strategies",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam sollicitudin mollis eros quis dapibus. Proin non gravida eros, vel vehicula purus."
            ),
            Item(
                title = "Portfolio Management",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam sollicitudin mollis eros quis dapibus. Proin non gravida eros, vel vehicula purus."
            ),
            Item(
                title = "Market Analysis Guide",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam sollicitudin mollis eros quis dapibus. Proin non gravida eros, vel vehicula purus."
            )
        )


        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = ForumAdapter(items)
    }
}