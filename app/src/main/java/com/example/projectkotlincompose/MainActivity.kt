package com.example.projectkotlincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.projectkotlincompose.composables.NavGraph
import com.example.projectkotlincompose.data.PostRepository
import com.example.projectkotlincompose.domain.GetPostsUseCase
import com.example.projectkotlincompose.viewmodel.PostViewModel
import com.example.projectkotlincompose.viewmodel.UserViewModel
import com.example.projectkotlincompose.ui.theme.ProjectKotlinComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val postRepository = PostRepository()
        val postUseCase = GetPostsUseCase(postRepository)
        val postViewModel = PostViewModel(postUseCase)

        // UserViewModel avec un Factory
        val userViewModel = UserViewModel(applicationContext)

        setContent {
            ProjectKotlinComposeTheme {
                NavGraph(viewModel = postViewModel, userViewModel = userViewModel)
            }
        }
    }
}

