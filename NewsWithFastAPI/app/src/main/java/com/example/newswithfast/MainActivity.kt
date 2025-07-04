package com.example.newswithfast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.androidnetworking.AndroidNetworking
import com.example.newswithfast.composables.NewsScreen
import com.example.newswithfast.ui.theme.NewsWithFastTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // this is the initializingg part
        AndroidNetworking.initialize(applicationContext)
        enableEdgeToEdge()
        setContent {
            NewsWithFastTheme {
              NewsScreen()
            }
        }
    }
}