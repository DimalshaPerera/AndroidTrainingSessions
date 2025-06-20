package com.example.custom

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val profileCard = findViewById<CardView>(R.id.profileCard)
        profileCard.setName("John Doe")
        profileCard.setAge("28")
        profileCard.setImage(R.drawable.user)


        val profileCard2 = findViewById<CardView>(R.id.profileCard2)
        profileCard2.setName("Cillian Murphy")
        profileCard2.setAge("45")
        profileCard2.setImage(R.drawable.cillian)
    }

}