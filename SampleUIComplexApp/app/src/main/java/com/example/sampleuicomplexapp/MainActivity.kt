package com.example.sampleuicomplexapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout)

        actionBarDrawerToggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close)
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navView = findViewById<NavigationView>(R.id.nav_view)

        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_all -> {
                    Toast.makeText(this, "All Notes", Toast.LENGTH_SHORT).show()
                }
                R.id.nav_settings -> {
                    startActivity(Intent(this, Settings::class.java))
                }
                R.id.nav_About -> {
                    startActivity(Intent(this, About::class.java))
                }
            }
            drawerLayout.closeDrawers()
            true
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val items = listOf(
            Item("Note 1", "Description for note 1"),
            Item("Note 2", "Description for note 2"),
            Item("Note 3", "Description for note 3"),
            Item("Note 4", "Description for note 4"),
            Item("Note 5", "Description for note 5"),
            Item("Note 6", "Description for note 6"),
            Item("Note 7", "Description for note 7"),
            Item("Note 8", "Description for note 8"),
            Item("Note 9", "Description for note 9"),
            Item("Note 10", "Description for note 10"),
            Item("Note 11", "Description for note 11"),
            Item("Note 12", "Description for note 12"),
            Item("Note 13", "Description for note 13"),
            Item("Note 14", "Description for note 14"),
            Item("Note 15", "Description for note 15"),
            Item("Note 16", "Description for note 16")
        )


        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = SimpleAdapter(items)



    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }



}

