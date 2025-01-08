package com.example.zadumite_frontend.di

import com.example.zadumite_frontend.data.repository.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get()) }
}