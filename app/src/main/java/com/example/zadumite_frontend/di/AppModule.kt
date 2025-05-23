package com.example.zadumite_frontend.di

val appModule = listOf(
    networkModule,
    jwtTokenModule,
    interceptorModule,
    sessionModule,
    authenticatorModule,
    repositoryModule,
    useCaseModule,
    viewModelModule
)