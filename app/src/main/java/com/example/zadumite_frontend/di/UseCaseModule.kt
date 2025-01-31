package com.example.zadumite_frontend.di

import com.example.zadumite_frontend.domain.AddWordUseCase
import com.example.zadumite_frontend.domain.FetchUserWordsUseCase
import com.example.zadumite_frontend.domain.FetchWordUseCase
import com.example.zadumite_frontend.domain.LogInUseCase
import com.example.zadumite_frontend.domain.SignUpUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory{ AddWordUseCase(get()) }
    factory{ SignUpUseCase(get()) }
    factory{ LogInUseCase(get()) }
    factory{ FetchWordUseCase(get()) }
    factory{ FetchUserWordsUseCase(get(), get())}
}