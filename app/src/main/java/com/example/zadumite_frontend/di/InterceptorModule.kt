package com.example.zadumite_frontend.di

import com.example.zadumite_frontend.data.interception.AccessTokenInterceptor
import com.example.zadumite_frontend.data.interception.RefreshTokenInterceptor
import org.koin.dsl.module

val interceptorModule = module {
    single { AccessTokenInterceptor(get()) }
    single { RefreshTokenInterceptor(get()) }
}