package com.example.zadumite_frontend.domain

import com.example.zadumite_frontend.data.model.token.TokenResponse
import com.example.zadumite_frontend.data.model.user.LogInRequest
import com.example.zadumite_frontend.data.repository.AuthRepository

class LogInUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: LogInRequest): TokenResponse {
        return authRepository.logIn(user)
    }
}
