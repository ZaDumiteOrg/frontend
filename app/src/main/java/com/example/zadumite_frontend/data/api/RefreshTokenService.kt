package com.example.zadumite_frontend.data.api

import com.example.zadumite_frontend.data.model.token.RefreshTokenRequest
import com.example.zadumite_frontend.data.model.token.TokenResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface RefreshTokenService {
    @POST("auth/refresh")
    suspend fun refreshToken(@Body refreshTokenRequest: RefreshTokenRequest): retrofit2.Response<TokenResponse>
}


