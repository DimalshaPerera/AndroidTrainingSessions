package com.example.puredependency

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.puredependency.composables.UserScreen
import com.example.puredependency.repository.UserRepositoryImpl
import com.example.puredependency.ui.theme.PureDependencyTheme
import com.example.puredependency.viewModels.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val userRepository=UserRepositoryImpl()
        val viewModel=UserViewModel(userRepository)
        enableEdgeToEdge()
        setContent {
            PureDependencyTheme {
                UserScreen(viewModel)
                }
            }
        }
    }



