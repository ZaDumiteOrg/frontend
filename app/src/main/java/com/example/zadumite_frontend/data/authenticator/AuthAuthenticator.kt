package com.example.zadumite_frontend.data.authenticator

import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AuthAuthenticator(
    private val tokenManager: JwtTokenManager,
    private val refreshTokenHandler: RefreshTokenHandler
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val refreshToken = tokenManager.getRefreshJwt()
            if (refreshToken.isNullOrEmpty()) return@runBlocking null

            val newTokenResponse = refreshTokenHandler.refreshAccessToken(refreshToken)
            if (newTokenResponse != null) {
                tokenManager.saveAccessJwt(newTokenResponse.accessToken)
                tokenManager.saveRefreshJwt(newTokenResponse.refreshToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${newTokenResponse.accessToken}")
                    .build()
            } else {
                null
            }
        }
    }
}
