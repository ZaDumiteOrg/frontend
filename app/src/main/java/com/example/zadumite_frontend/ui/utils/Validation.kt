package com.example.zadumite_frontend.ui.utils

fun isValidEmail(email: String): Boolean {
    val emailPattern = Regex("^[a-zA-Z0-9._-]+@[a-zA-Z]+\\.[a-zA-Z]{2,}$")
    return emailPattern.matches(email)
}

fun isValidPassword(password: String): Boolean {
    val passwordPattern = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
    return passwordPattern.matches(password)
}