package com.example.zadumite_frontend.navigation

sealed class Screen(val route: String) {
    object Start: Screen("start_screen")
    object SignUp: Screen("signup_screen")
    object LogIn: Screen("login_screen")
    object Word: Screen("word_screen")
    object UserWords: Screen("user_words_screen/{userId}")
}