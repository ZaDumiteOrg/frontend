package com.example.zadumite_frontend.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.zadumite_frontend.data.model.datastore.JwtTokenDataStore
import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import com.example.zadumite_frontend.di.providers.provideDataStore
import org.koin.dsl.module


val jwtTokenModule = module {
    single<JwtTokenManager> { JwtTokenDataStore(get()) }
    single<DataStore<Preferences>> { provideDataStore(get()) }
}

