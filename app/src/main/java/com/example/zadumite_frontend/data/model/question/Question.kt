package com.example.zadumite_frontend.data.model.question

data class Question(
    val id: Int,
    val questionText: String,
    val optionA: String,
    val optionB: String,
    val optionC: String? = null,
    val optionD: String? = null,
    val correctOption: String
)
