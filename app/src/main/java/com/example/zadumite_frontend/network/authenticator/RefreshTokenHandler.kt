package com.example.zadumite_frontend.network.authenticator

import com.example.zadumite_frontend.data.model.token.RefreshTokenRequest
import com.example.zadumite_frontend.data.model.token.TokenResponse
import com.example.zadumite_frontend.network.RefreshTokenService

class RefreshTokenHandler(
    private val refreshTokenService: RefreshTokenService
) {
    suspend fun refreshAccessToken(refreshToken: String): TokenResponse? {
        return try {
            val response = refreshTokenService.refreshToken(RefreshTokenRequest(refreshToken))
            if (response.isSuccessful) {
                response.body()
            } else {
                println("Failed to refresh token: ${response.code()} - ${response.message()}")
                null
            }
        } catch (e: Exception) {
            println("Exception during token refresh: ${e.message}")
            null
        }
    }
}
