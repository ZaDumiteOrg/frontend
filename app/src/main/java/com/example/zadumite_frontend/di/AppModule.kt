package com.example.zadumite_frontend.di

val appModule = listOf(
    jwtTokenModule,
    interceptorModule,
    authenticatorModule,
    networkModule,
    repositoryModule,
    viewModelModule,
    tokenManagerModule,
)