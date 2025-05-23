package com.example.zadumite_frontend.domain

import com.example.zadumite_frontend.data.model.question.UserAnswerRequest
import com.example.zadumite_frontend.data.model.question.UserQuestion
import com.example.zadumite_frontend.data.repository.DailyQuestionRepository

class SubmitUserAnswerUseCase(private val repository: DailyQuestionRepository) {
    suspend operator fun invoke(request: UserAnswerRequest): Result<UserQuestion> {
        return repository.submitUserAnswer(request)
    }
}