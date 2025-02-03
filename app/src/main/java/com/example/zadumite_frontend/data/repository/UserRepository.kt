package com.example.zadumite_frontend.data.repository

import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import com.example.zadumite_frontend.data.model.user.User
import com.example.zadumite_frontend.network.ZaDumiteApiService

class UserRepository(
    private val apiService: ZaDumiteApiService,
    private val tokenManager: JwtTokenManager
){
    suspend fun getUserById(id: Int): User {
        return apiService.getUserById(id)
    }

    suspend fun logout() {
        tokenManager.clearAllTokens()
    }
}