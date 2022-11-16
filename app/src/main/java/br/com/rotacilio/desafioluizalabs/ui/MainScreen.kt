package br.com.rotacilio.desafioluizalabs.ui

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "repositories"
    ) {
        composable("repositories") {
            RepositoriesScreen { userName, repoName ->
                Log.d("MainScreen", "userName: $userName")
                Log.d("MainScreen", "repoName: $repoName")
            }
        }
    }
}