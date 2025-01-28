package com.example.zadumite_frontend.di
import com.example.zadumite_frontend.ui.signup.SignUpViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::SignUpViewModel)
}
