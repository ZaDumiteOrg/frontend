package com.example.zadumite_frontend.di

import com.example.zadumite_frontend.data.repository.AuthRepository
import com.example.zadumite_frontend.data.repository.DailyQuestionRepository
import com.example.zadumite_frontend.data.repository.UserRepository
import com.example.zadumite_frontend.data.repository.WordRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get(), get()) }
    single { WordRepository(get(), get()) }
    single{ UserRepository(get(), get()) }
    single{ DailyQuestionRepository(get()) }
}