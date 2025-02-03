package com.example.zadumite_frontend.domain

import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import com.example.zadumite_frontend.data.model.user.User
import com.example.zadumite_frontend.data.repository.UserRepository

class GetUserUseCase(
    private val userRepository: UserRepository,
    private val tokenManager: JwtTokenManager
) {
    suspend operator fun invoke(): User {
        val userId = tokenManager.getUserId() ?: throw Exception("User ID not found")
        return userRepository.getUserById(userId)
    }
}