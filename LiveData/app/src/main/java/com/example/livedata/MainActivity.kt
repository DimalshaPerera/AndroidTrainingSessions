package com.example.livedata

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.startCounter()

        val textView: TextView = findViewById(R.id.textView)
        viewModel.seconds().observe(this) {
            textView.text = "$it"
        }
        viewModel.finished().observe(this) {
            if (it) {
                textView.text = "Finished"
            }
        }
    }
}