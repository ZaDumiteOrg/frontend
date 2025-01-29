package com.example.zadumite_frontend.di

import com.example.zadumite_frontend.network.RefreshTokenService
import com.example.zadumite_frontend.network.authenticator.AuthAuthenticator
import com.example.zadumite_frontend.network.authenticator.RefreshTokenHandler
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val authenticatorModule = module {
    single<RefreshTokenService> {
        get<Retrofit>(named("RefreshRetrofit")).create(RefreshTokenService::class.java)
    }

    single {
        RefreshTokenHandler(get())
    }

    single {
        AuthAuthenticator(
            tokenManager = get(),
            refreshTokenHandler = get()
        )
    }
}
