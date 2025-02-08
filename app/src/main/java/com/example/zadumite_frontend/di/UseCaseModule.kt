package com.example.zadumite_frontend.di

import com.example.zadumite_frontend.domain.AddWordUseCase
import com.example.zadumite_frontend.domain.FetchUserWordsUseCase
import com.example.zadumite_frontend.domain.FetchWordUseCase
import com.example.zadumite_frontend.domain.GetUserUseCase
import com.example.zadumite_frontend.domain.LogInUseCase
import com.example.zadumite_frontend.domain.LogoutUseCase
import com.example.zadumite_frontend.domain.SignUpUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory{ AddWordUseCase(get()) }
    factory{ SignUpUseCase(get()) }
    factory{ LogInUseCase(get()) }
    factory{ FetchWordUseCase(get()) }
    factory{ FetchUserWordsUseCase(get(), get()) }
    factory{ GetUserUseCase(get(), get()) }
    factory{ LogoutUseCase(get()) }
}