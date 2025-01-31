package com.example.zadumite_frontend.domain

import com.example.zadumite_frontend.data.model.user.SignUpRequest
import com.example.zadumite_frontend.data.model.user.SignUpResponse
import com.example.zadumite_frontend.data.repository.AuthRepository

class SignUpUseCase(private val authRepository: AuthRepository) {
    suspend operator fun invoke(user: SignUpRequest): SignUpResponse {
        return authRepository.signUp(user)
    }
}
