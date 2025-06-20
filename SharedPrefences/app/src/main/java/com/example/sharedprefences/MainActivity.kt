package com.example.sharedprefences

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.edit1)
        ageEditText = findViewById(R.id.edit2)

    }
    override fun onResume() {
        super.onResume()
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val savedName = sharedPreferences.getString("user_name", "")
        val savedAge = sharedPreferences.getInt("user_age", 0)
        nameEditText.setText(savedName)
        ageEditText.setText(if (savedAge > 0) savedAge.toString() else "")
    }

    override fun onPause() {
        super.onPause()
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("user_name", nameEditText.text.toString())
        val ageInput = ageEditText.text.toString()
        val userAge = if (ageInput.isEmpty()) 0 else ageInput.toInt()
        editor.putInt("user_age", userAge)
        editor.apply()
    }
}