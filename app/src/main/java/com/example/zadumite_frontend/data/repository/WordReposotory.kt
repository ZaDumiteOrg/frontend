package com.example.zadumite_frontend.data.repository

import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.data.api.ZaDumiteApiService
import com.example.zadumite_frontend.utils.token.TokenUtils.decodeUserRoleFromToken
import retrofit2.Response

class WordRepository(
    private val apiService: ZaDumiteApiService,
    private val jwtTokenDataStore: JwtTokenManager
) {
    suspend fun addWord(wordRequest: Word): Result<Word> {
        val accessToken = jwtTokenDataStore.getAccessJwt()
        if (accessToken.isNullOrBlank()) {
            return Result.failure(Exception("Access token is missing."))
        }

        val userRole = decodeUserRoleFromToken(accessToken)
        if (userRole != "admin") {
            return Result.failure(Exception("Unauthorized: Only admins can add words."))
        }

        return try {
            val response = apiService.addWord(wordRequest)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to add word: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
