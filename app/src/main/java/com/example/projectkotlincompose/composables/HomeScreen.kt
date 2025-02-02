package com.example.projectkotlincompose.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(navController, drawerState, coroutineScope)
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Accueil") },
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Bienvenue sur l'application!",
                    style = MaterialTheme.typography.headlineMedium
                )

//                Spacer(modifier = Modifier.height(16.dp))
//
//                Button(
//                    onClick = {
//                        navController.navigate("postList")
//                    },
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text(text = "Voir la liste des posts")
//                }
            }
        }
    }
}

@Composable
fun DrawerContent(
    navController: NavHostController,
    drawerState: DrawerState,
    coroutineScope: kotlinx.coroutines.CoroutineScope
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.50f)
            .padding(16.dp)
    ) {
        Text(
            text = "Menu",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                coroutineScope.launch { drawerState.close() }
                navController.navigate("profile")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Profil")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                coroutineScope.launch { drawerState.close() }
                navController.navigate("about")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("À propos")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                coroutineScope.launch { drawerState.close() }
                navController.navigate("postList")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Voir les posts")
        }
    }
}
