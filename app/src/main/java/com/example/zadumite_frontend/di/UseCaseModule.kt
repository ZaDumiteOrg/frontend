package com.example.zadumite_frontend.di

import com.example.zadumite_frontend.domain.AddWordUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory{ AddWordUseCase(get()) }
}