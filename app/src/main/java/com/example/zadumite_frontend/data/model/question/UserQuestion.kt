package com.example.zadumite_frontend.data.model.question

import java.util.Date

data class UserQuestion(
    val id: Int,
    val selectedOption: String,
    val isCorrect: Boolean,
    val answeredAt: Date,
)
