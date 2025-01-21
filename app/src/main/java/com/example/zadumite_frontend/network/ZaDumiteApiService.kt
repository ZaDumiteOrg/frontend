package com.example.zadumite_frontend.network

import com.example.zadumite_frontend.data.model.token.TokenResponse
import com.example.zadumite_frontend.data.model.user.LogInRequest
import com.example.zadumite_frontend.data.model.user.SignUpRequest
import com.example.zadumite_frontend.data.model.user.SignUpResponse
import com.example.zadumite_frontend.data.model.word.Word
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ZaDumiteApiService {
    @POST("auth/register")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @POST("auth/login")
    suspend fun logIn(@Body request: LogInRequest): Response<TokenResponse>

    @GET("user/word-of-the-week")
    suspend fun getWordOfTheWeek(): Word

    @GET("user/{id}/words")
    suspend fun getUserWords(@Path("id") userId: Int): List<Word>
}