package com.example.zadumite_frontend.di

import com.example.zadumite_frontend.network.authenticator.AuthAuthenticator
import org.koin.dsl.module


val authenticatorModule = module {
    single { AuthAuthenticator(get(), get()) }
}