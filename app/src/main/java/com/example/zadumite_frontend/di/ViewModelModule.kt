package com.example.zadumite_frontend.di
import com.example.zadumite_frontend.ui.login.LogInViewModel
import com.example.zadumite_frontend.ui.signup.SignUpViewModel
import com.example.zadumite_frontend.ui.word.WordViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { LogInViewModel(get()) }
    viewModel { WordViewModel(get()) }
}
