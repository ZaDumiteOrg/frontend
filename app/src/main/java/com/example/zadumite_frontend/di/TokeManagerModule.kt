package com.example.zadumite_frontend.di

import com.example.zadumite_frontend.data.model.datastore.JwtTokenDataStore
import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import org.koin.dsl.module

val tokenManagerModule = module {
    single<JwtTokenManager> { JwtTokenDataStore(dataStore = get()) }
}