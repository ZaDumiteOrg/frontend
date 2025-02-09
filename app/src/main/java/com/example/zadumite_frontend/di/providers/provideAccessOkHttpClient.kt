package com.example.zadumite_frontend.di.providers

import com.example.zadumite_frontend.data.authenticator.AuthAuthenticator
import com.example.zadumite_frontend.data.interception.AccessTokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

fun provideAccessOkHttpClient(
    accessTokenInterceptor: AccessTokenInterceptor,
    authAuthenticator: AuthAuthenticator
): OkHttpClient {
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .authenticator(authAuthenticator)
        .addInterceptor(accessTokenInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()
}