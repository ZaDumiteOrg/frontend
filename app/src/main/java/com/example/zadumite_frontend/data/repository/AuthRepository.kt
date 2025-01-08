package com.example.zadumite_frontend.data.repository

import ZaDumiteApiService
import com.example.zadumite_frontend.data.model.user.SignUpRequest
import com.example.zadumite_frontend.data.model.user.SignUpResponse

class AuthRepository(private val apiService: ZaDumiteApiService) {
    suspend fun signUp(user: SignUpRequest): SignUpResponse {
        try{
            val response = apiService.signUp(user)

            if (response.isSuccessful) {
                return response.body() ?: throw Exception("Empty response body")
            } else {
                throw Exception("Error signing up: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            println("Exception during sign up: ${e.message}")
            throw e
        }

    }
}
