package com.example.zadumite_frontend.domain

import com.example.zadumite_frontend.data.repository.UserRepository

class LogoutUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke() {
        userRepository.logout()
    }
}
