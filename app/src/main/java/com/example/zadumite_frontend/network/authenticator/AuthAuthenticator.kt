package com.example.zadumite_frontend.network.authenticator

import com.example.zadumite_frontend.network.ZaDumiteApiService
import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import com.example.zadumite_frontend.data.model.token.RefreshTokenRequest
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.withLock
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AuthAuthenticator(
    private val tokenManager: JwtTokenManager,
    private val apiService: ZaDumiteApiService
) : Authenticator {

    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }

    private val mutex = kotlinx.coroutines.sync.Mutex()

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            mutex.withLock {
                val refreshToken = tokenManager.getRefreshJwt()
                if (refreshToken.isNullOrEmpty()) return@withLock null

                val newAccessToken = refreshAccessToken(refreshToken)
                if (!newAccessToken.isNullOrEmpty()) {
                    response.request.newBuilder()
                        .header(HEADER_AUTHORIZATION, "$TOKEN_TYPE $newAccessToken")
                        .build()
                } else {
                    null
                }
            }
        }
    }

    private suspend fun refreshAccessToken(refreshToken: String): String? {
        return try {
            val refreshTokenRequest = RefreshTokenRequest(refreshToken = refreshToken)
            val refreshResponse = apiService.refreshToken(refreshTokenRequest)

            if (refreshResponse.isSuccessful && refreshResponse.body() != null) {
                val body = refreshResponse.body()!!
                tokenManager.saveAccessJwt(body.accessToken)
                tokenManager.saveRefreshJwt(body.refreshToken)
                body.accessToken
            } else {
                null
            }
        } catch (e: Exception) {
            println("Error refreshing access token: ${e.message}")
            null
        }
    }
}
