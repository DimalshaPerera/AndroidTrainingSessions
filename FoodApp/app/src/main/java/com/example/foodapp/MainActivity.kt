package com.example.foodapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
       binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.burgerButton.setOnClickListener{
            val intent= Intent(this,BurgerView::class.java)
            startActivity(intent)
    }


        binding.homeButton.setOnClickListener{
            val intent= Intent(this,Home::class.java)
            startActivity(intent)
        }

        binding.listButton.setOnClickListener{
            val intent= Intent(this,TabLayoutView::class.java)
            startActivity(intent)
        }
//        binding.list2Button.setOnClickListener{
//            val intent= Intent(this,TabLayout::class.java)
//            startActivity(intent)
//        }
    }
}