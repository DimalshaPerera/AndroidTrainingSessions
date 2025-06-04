package com.example.xmlactivity.LayoutScreens

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.xmlactivity.R

class GridLayout : AppCompatActivity(), View.OnClickListener {

    private val animalNames =
        arrayOf("Cat", "Dog", "Bird", "Butterfly", "Duck", "Lion", "Penguin", "Rabbit")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_layout)
        val imageViewIds = intArrayOf(
            R.id.imageView, R.id.imageView2, R.id.imageView3, R.id.imageView4,
            R.id.imageView5, R.id.imageView6, R.id.imageView7, R.id.imageView8
        )


        imageViewIds.forEachIndexed { index, id ->
            val imageView = findViewById<ImageView>(id)
            imageView.tag = index
            imageView.setOnClickListener(this)
        }
    }

    override fun onClick(v: View) {
        val index = v.tag as Int
        val animalName = animalNames[index]
        Toast.makeText(this, "$animalName clicked!", Toast.LENGTH_LONG).show()
    }
}