package com.example.zadumite_frontend.network

import com.example.zadumite_frontend.data.model.token.TokenResponse
import com.example.zadumite_frontend.data.model.user.LogInRequest
import com.example.zadumite_frontend.data.model.user.SignUpRequest
import com.example.zadumite_frontend.data.model.user.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ZaDumiteApiService {
    @POST("auth/register")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @POST("auth/login")
    suspend fun logIn(@Body request: LogInRequest): Response<TokenResponse>
}