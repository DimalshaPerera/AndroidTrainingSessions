package com.example.firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.firebase.ui.theme.FireBaseTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        FirebaseApp.initializeApp(this)
        setContent {
            FireBaseTheme {
AuthApp()
            }
        }
    }
}

@Composable
fun AuthApp(){
    val navController= rememberNavController()
    NavHost(navController, startDestination = "login"){
        composable ( "login"){LoginScreen(navController)}
        composable (  "signup"){SignUpScreen(navController)}
        composable (  "home"){HomeScreen(navController)}
    }
}