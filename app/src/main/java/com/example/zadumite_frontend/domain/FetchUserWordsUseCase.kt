package com.example.zadumite_frontend.domain

import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.data.api.ZaDumiteApiService

class FetchUserWordsUseCase(
    private val apiService: ZaDumiteApiService,
    private val tokenManager: JwtTokenManager
) {
    suspend operator fun invoke():  Result<List<Word>> {
        return try {
            val userId = tokenManager.getUserId() ?: throw Exception("User ID not found")
            val words = apiService.getUserWords(userId)
            Result.success(words)
        }catch (e: Exception) {
            Result.failure(e)
        }
    }
}