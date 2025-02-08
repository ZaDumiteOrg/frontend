package com.example.zadumite_frontend.ui.utils.validation

fun isValidPassword(password: String): Boolean {
    val passwordPattern = Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
    return passwordPattern.matches(password)
}