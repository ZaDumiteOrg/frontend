package com.example.zadumite_frontend.navigation

sealed class Screen(val route: String) {
    data object Start: Screen("start_screen")
    data object SignUp: Screen("signup_screen")
    data object LogIn: Screen("login_screen")
    data object Word: Screen("word_screen")
    data object UserWords : Screen("user_words_screen")
    data object Profile: Screen("profile_screen")
    data object AddWord: Screen("add_word_screen")

}