package com.example.puredependency.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.puredependency.viewModels.UserViewModel

@Composable
fun UserScreen(viewModel: UserViewModel) {
    LaunchedEffect(Unit) {
        viewModel.loadUser()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = viewModel.userName,
            fontSize = 24.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}