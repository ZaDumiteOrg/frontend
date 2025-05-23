package com.example.zadumite_frontend.data.api

import com.example.zadumite_frontend.data.model.question.Question
import com.example.zadumite_frontend.data.model.question.UserAnswerRequest
import com.example.zadumite_frontend.data.model.question.UserQuestion
import com.example.zadumite_frontend.data.model.token.TokenResponse
import com.example.zadumite_frontend.data.model.user.LogInRequest
import com.example.zadumite_frontend.data.model.user.SignUpRequest
import com.example.zadumite_frontend.data.model.user.SignUpResponse
import com.example.zadumite_frontend.data.model.user.User
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

    @GET("user/{id}")
    suspend fun getUserById(@Path("id") userId: Int): User

    @GET("user")
    suspend fun getAllUsers(): Response<List<User>>

    @POST("word")
    suspend fun addWord(@Body request: Word): Response<Word>

    @GET("question/daily")
    suspend fun getDailyQuestion(): Response<Question>

    @POST("user-questions")
    suspend fun submitUserAnswer(@Body request: UserAnswerRequest): Response<UserQuestion>

    @GET("user-questions/score/{userId}")
    suspend fun getUserScore(@Path("userId") userId: Int): Response<Int>
}