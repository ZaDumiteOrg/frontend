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
            StartScreen(
                onNavigateToSignUp = { navController.navigate(Screen.SignUp.route) },
                onNavigateToLogIn = { navController.navigate(Screen.LogIn.route) }
            )
        }
        composable(
            route = Screen.SignUp.route,
        ) {
            SignUpScreen(onNavigateBack = { navController.popBackStack() })
        }
        composable(
            route = Screen.LogIn.route,
        ) {
            LogInScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}