package com.example.xmlactivity

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_view)

        val listview: ListView = findViewById(R.id.list)
        val listItems = arrayOf("Read a book", "Go shopping", "Learn", "Attend a seminar", "Join the lecture", "Listen to Music", "Dance", "Workout", "Play guitar", "donate", "pilates", "yoga", "bake ", "travel", "learn ballet", "cant st", "Join the lecture", "Join the lecture", "Join the lecture", "Join the lecture", "Join the lecture", "Join the lecture", "Join the lecture", "Join the lecture", "Join the lecture", "Join the lecture", "Join the lecture", "Join the lectu, \"Join the lecture\"re")


        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
        listview.adapter = listAdapter

        listview.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as String
            Toast.makeText(this, "You have clicked on: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }
}