package com.example.projectkotlincompose.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projectkotlincompose.data.Post
import com.example.projectkotlincompose.viewmodel.PostViewModel

@Composable
fun PostListScreen(
    navController: NavHostController,
    viewModel: PostViewModel
) {
    val posts = viewModel.posts.collectAsState()

    // Charge les posts dès que l'écran est affiché
    LaunchedEffect(Unit) {
        viewModel.fetchPosts()
    }

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(posts.value) { post ->
                PostItem(post) {
                    navController.navigate("postDetail/${post.id}")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Retour")
        }
    }
}

@Composable
fun PostItem(post: Post, onClick: () -> Unit) {
    Card(onClick = onClick, modifier = Modifier.padding(8.dp)) {
        Text(text = post.title, modifier = Modifier.padding(16.dp))
    }
}
