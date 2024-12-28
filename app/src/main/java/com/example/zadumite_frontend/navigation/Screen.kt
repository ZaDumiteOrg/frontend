package com.example.zadumite_frontend.navigation

sealed class Screen(val route: String) {
    object Start: Screen("start_screen")
    object SignUp: Screen("signup_screen")
    object LogIn: Screen("login_screen")
    object Main: Screen("main_screen")
}