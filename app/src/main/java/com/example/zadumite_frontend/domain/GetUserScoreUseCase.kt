package com.example.zadumite_frontend.domain

import com.example.zadumite_frontend.data.repository.DailyQuestionRepository

class GetUserScoreUseCase(
    private val dailyQuestionRepository: DailyQuestionRepository,
    private val getUserUseCase: GetUserUseCase
) {
    suspend operator fun invoke(): Result<Int> {
        return try {
            val user = getUserUseCase()
            dailyQuestionRepository.getUserScore(user.id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
