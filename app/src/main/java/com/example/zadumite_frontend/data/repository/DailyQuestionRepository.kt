package com.example.zadumite_frontend.data.repository

import com.example.zadumite_frontend.data.api.ZaDumiteApiService
import com.example.zadumite_frontend.data.model.question.Question
import com.example.zadumite_frontend.data.model.question.UserAnswerRequest
import com.example.zadumite_frontend.data.model.question.UserQuestion

class DailyQuestionRepository(
    private val apiService: ZaDumiteApiService
) {
    suspend fun getDailyQuestion(): Result<Question> {
        return try {
            val response = apiService.getDailyQuestion()
            if (response.isSuccessful) {
                val question = response.body()
                if (question != null) {
                    Result.success(question)
                } else {
                    Result.failure(Exception("Empty response body"))
                }
            } else {
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun submitUserAnswer(request: UserAnswerRequest): Result<UserQuestion> {
        return try {
            val response = apiService.submitUserAnswer(request)
            if(response.isSuccessful) {
                val userResponse = response.body()
                if(userResponse != null) {
                    Result.success(userResponse)
                } else {
                    Result.failure(Exception("Empty response body"))
                }
            } else {
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserScore(userId: Int): Result<Int> {
        return try {
            val response = apiService.getUserScore(userId)
            if (response.isSuccessful) {
                val score = response.body()
                if (score != null) {
                    Result.success(score)
                } else {
                    Result.failure(Exception("Empty score response"))
                }
            } else {
                Result.failure(Exception("Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
