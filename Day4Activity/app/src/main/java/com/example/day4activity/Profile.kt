package com.example.day4activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.day4activity.databinding.ActivityProfileBinding
import com.example.day4activity.databinding.BottomNavBinding

class Profile : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var bottomNavBinding: BottomNavBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        bottomNavBinding = BottomNavBinding.bind(binding.root.findViewById(R.id.bottom_nav_include))
        setContentView(binding.root)
        setupBottomNavigation()
        setupMenuItems()
    }

    private fun setupBottomNavigation() {
        bottomNavBinding.imageView.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        bottomNavBinding.profile.setOnClickListener {
            Toast.makeText(this, "Already on this page", Toast.LENGTH_LONG).show()
        }
    }

    private fun setupMenuItems() {

        binding.personalDataItem.setOnClickListener {
            startActivity(Intent(this, PersonalData::class.java))
        }


        binding.communityItem.setOnClickListener {
            startActivity(Intent(this, Community::class.java))
        }
    }
}