package com.example.zadumite_frontend.data.repository

import com.example.zadumite_frontend.network.ZaDumiteApiService
import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import com.example.zadumite_frontend.data.model.token.TokenResponse
import com.example.zadumite_frontend.data.model.user.LogInRequest
import com.example.zadumite_frontend.data.model.user.SignUpRequest
import com.example.zadumite_frontend.data.model.user.SignUpResponse
import com.example.zadumite_frontend.utils.token.TokenUtils

class AuthRepository(
    private val apiService: ZaDumiteApiService,
    private val tokenManager: JwtTokenManager
) {
    suspend fun signUp(user: SignUpRequest): SignUpResponse {
        try{
            val response = apiService.signUp(user)

            if (response.isSuccessful && response.body() != null) {
                val signUpResponse = response.body()!!
                tokenManager.saveAccessJwt(signUpResponse.accessToken)
                tokenManager.saveRefreshJwt(signUpResponse.refreshToken)
                val userId = TokenUtils.decodeUserIdFromToken(signUpResponse.accessToken)
                if (userId != null) {
                    tokenManager.saveUserId(userId)
                } else {
                    throw Exception("No user id: ${response.code()} - ${response.message()} - ${response.errorBody()?.string()}")
                }
                return signUpResponse
            } else {
                throw Exception("Error signing up: ${response.code()} - ${response.message()}")
            }
        } catch (e: Exception) {
            println("Exception during sign up: ${e.message}")
            throw e
        }
    }

    suspend fun logIn(user: LogInRequest): TokenResponse {
        try {
            val response = apiService.logIn(user)
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                tokenManager.saveAccessJwt(body.accessToken)
                tokenManager.saveRefreshJwt(body.refreshToken)
                val userId = TokenUtils.decodeUserIdFromToken(body.accessToken)
                if (userId != null) {
                    tokenManager.saveUserId(userId)
                } else {
                    throw Exception("No user id: ${response.code()} - ${response.message()} - ${response.errorBody()?.string()}")
                }
                return body
            } else {
                throw Exception("Login failed: ${response.code()} - ${response.message()} - ${response.errorBody()?.string()}")
            }
        } catch (e: Exception) {
            println("Exception during log in: ${e.message}")
            throw e
        }
    }
}
