package com.example.zadumite_frontend
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun NavigationStack() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Start.route) {
        composable(route = Screen.Start.route) {
            StartScreen(navController = navController)
        }
        composable(
            route = Screen.SignUp.route,
        ) {
            SignUpScreen(navController = navController)
        }
        composable(
            route = Screen.LogIn.route,
        ) {
            LogInScreen(navController = navController)
        }
    }
}