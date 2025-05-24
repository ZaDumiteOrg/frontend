package com.example.zadumite_frontend.di

import com.example.zadumite_frontend.data.model.session.DefaultSessionManager
import com.example.zadumite_frontend.data.model.session.LogoutNotifier
import com.example.zadumite_frontend.data.model.session.SessionManager
import org.koin.dsl.module

val sessionModule = module {
    single { LogoutNotifier() }
    single<SessionManager> { DefaultSessionManager(get(), get()) }
}