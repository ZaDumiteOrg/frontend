package com.example.zadumite_frontend.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.zadumite_frontend.LogInScreen
import com.example.zadumite_frontend.SignUpScreen
import com.example.zadumite_frontend.StartScreen

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