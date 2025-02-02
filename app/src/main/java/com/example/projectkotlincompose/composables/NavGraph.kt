package com.example.projectkotlincompose.composables

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectkotlincompose.viewmodel.PostViewModel
import com.example.projectkotlincompose.viewmodel.UserViewModel

@Composable
fun NavGraph(
    viewModel: PostViewModel,
    userViewModel: UserViewModel
    ) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login" // Définit "login" comme page initiale
    ) {
        composable(route = "home") {
            HomeScreen(navController)
        }
        composable(route = "postList") {
            PostListScreen(navController, viewModel)
        }
        composable(route = "postDetail/{postId}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")?.toInt() ?: return@composable
            val post = viewModel.posts.value.find { it.id == postId } ?: return@composable
            PostDetailScreen(post)
        }
        composable(route = "login") {
            LoginScreen(navController, userViewModel)
        }
        composable(route = "register") {
            RegisterScreen(navController, userViewModel)
        }
        composable(route = "profile") {
            ProfileScreen(navController, userViewModel)
        }
        composable(route = "about") {
            AboutScreen(navController)
        }
    }

}


