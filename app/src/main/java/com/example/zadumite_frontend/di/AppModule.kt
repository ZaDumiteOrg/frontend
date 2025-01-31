package com.example.zadumite_frontend.di

val appModule = listOf(
    networkModule,
    jwtTokenModule,
    interceptorModule,
    authenticatorModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)