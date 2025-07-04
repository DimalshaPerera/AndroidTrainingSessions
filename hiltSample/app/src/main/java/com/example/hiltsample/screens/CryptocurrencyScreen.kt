package com.example.hiltsample.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hiltsample.composables.CryptocurrencyItem
import com.example.hiltsample.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CryptocurrencyScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val cryptocurrencies by viewModel.cryptocurrencies

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cryptocurrencies") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(cryptocurrencies) { cryptocurrency ->
                CryptocurrencyItem(cryptocurrency = cryptocurrency)
            }
        }
    }
}