package com.example.zadumite_frontend.data.model.token

interface JwtTokenManager {

    suspend fun saveUserId(userId: Int)
    suspend fun getUserId(): Int?
    suspend fun saveAccessJwt(accessToken: String)
    suspend fun saveRefreshJwt(refreshToken: String)
    suspend fun getAccessJwt(): String?
    suspend fun getRefreshJwt(): String?
    suspend fun clearAllTokens()
}
