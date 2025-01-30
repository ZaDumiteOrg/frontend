package com.example.zadumite_frontend.data.model.word

data class Word (
    val word: String,
    val description: String,
    val example: String,
    val synonym: String? = null
)