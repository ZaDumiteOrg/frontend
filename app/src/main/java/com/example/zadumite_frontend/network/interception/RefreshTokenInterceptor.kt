package com.example.zadumite_frontend.network.interception

import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class RefreshTokenInterceptor (
    private val tokenManager: JwtTokenManager
) : Interceptor {
    companion object {
        const val HEADER_AUTHORIZATION = "Authorization"
        const val TOKEN_TYPE = "Bearer"
    }
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            tokenManager.getRefreshJwt()
        }
        val request = chain.request().newBuilder().apply {
            if (!token.isNullOrEmpty()) {
                addHeader(HEADER_AUTHORIZATION, "$TOKEN_TYPE $token")
            }
        }.build()

        return chain.proceed(request)
    }
}