package com.example.fastapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.androidnetworking.AndroidNetworking
import com.example.fastapi.composables.NewsScreen
import com.example.fastapi.ui.theme.FastAPITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initializing part
        AndroidNetworking.initialize(applicationContext)
        enableEdgeToEdge()
        setContent {
            FastAPITheme {
           NewsScreen()
                }
            }
        }
    }


