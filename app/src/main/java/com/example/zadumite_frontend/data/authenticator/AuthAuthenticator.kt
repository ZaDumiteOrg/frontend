package com.example.zadumite_frontend.data.authenticator

import com.example.zadumite_frontend.data.model.session.SessionManager
import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

//intercepts failed requests due to expired JWT
class AuthAuthenticator(
    private val tokenManager: JwtTokenManager,
    private val refreshTokenHandler: RefreshTokenHandler,
    private val sessionManager: SessionManager
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            val refreshToken = tokenManager.getRefreshJwt()
            if (refreshToken.isNullOrEmpty()) {
                sessionManager.logout()
                return@runBlocking null
            }

            val newTokenResponse = refreshTokenHandler.refreshAccessToken(refreshToken)
            if (newTokenResponse != null) {
                tokenManager.saveAccessJwt(newTokenResponse.accessToken)
                tokenManager.saveRefreshJwt(newTokenResponse.refreshToken)
                response.request.newBuilder()
                    .header("Authorization", "Bearer ${newTokenResponse.accessToken}")
                    .build()
            } else {
                sessionManager.logout()
                null
            }
        }
    }
}
