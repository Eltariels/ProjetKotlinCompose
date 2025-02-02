package com.example.projectkotlincompose.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.projectkotlincompose.data.Post

@Composable
fun PostDetailScreen(post: Post) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = post.title, style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = post.body, style = MaterialTheme.typography.bodyLarge)
    }
}
