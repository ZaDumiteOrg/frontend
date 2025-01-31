package com.example.zadumite_frontend.domain

import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.network.ZaDumiteApiService

class FetchUserWordsUseCase(
    private val apiService: ZaDumiteApiService,
    private val tokenManager: JwtTokenManager
) {
    suspend operator fun invoke(): List<Word> {
        val userId = tokenManager.getUserId() ?: throw Exception("User ID not found")
        return apiService.getUserWords(userId)
    }
}