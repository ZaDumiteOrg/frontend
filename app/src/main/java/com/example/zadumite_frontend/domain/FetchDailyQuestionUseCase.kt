package com.example.zadumite_frontend.domain

import com.example.zadumite_frontend.data.model.question.Question
import com.example.zadumite_frontend.data.repository.DailyQuestionRepository

class FetchDailyQuestionUseCase(private val questionRepository : DailyQuestionRepository) {
    suspend operator fun invoke(): Result<Question> {
        return questionRepository.getDailyQuestion()
    }
}