package com.example.day4activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.day4activity.databinding.ActivityMainBinding
import com.example.day4activity.databinding.BottomNavBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavBinding: BottomNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        bottomNavBinding = BottomNavBinding.bind(binding.root.findViewById(R.id.bottom_nav_include))
        setContentView(binding.root)

        bottomNavBinding.profile.setOnClickListener {
            startActivity(Intent(this, Profile::class.java))
        }
    }
}