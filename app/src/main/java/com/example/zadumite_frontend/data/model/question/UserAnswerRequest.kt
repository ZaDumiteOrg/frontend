package com.example.zadumite_frontend.data.model.question

data class UserAnswerRequest(
    val userId: Int,
    val questionId: Int,
    val selectedOption: String
)
