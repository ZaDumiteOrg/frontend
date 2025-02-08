package com.example.zadumite_frontend.di
import com.example.zadumite_frontend.network.monitor.NetworkViewModel
import com.example.zadumite_frontend.ui.add_word.AddWordViewModel
import com.example.zadumite_frontend.ui.login.LogInViewModel
import com.example.zadumite_frontend.ui.profile.ProfileViewModel
import com.example.zadumite_frontend.ui.signup.SignUpViewModel
import com.example.zadumite_frontend.ui.user_words.UserWordsViewModel
import com.example.zadumite_frontend.ui.word.WordViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignUpViewModel(get()) }
    viewModel { LogInViewModel(get()) }
    viewModel { WordViewModel(get()) }
    viewModel { UserWordsViewModel(get()) }
    viewModel{ AddWordViewModel(get()) }
    viewModel { NetworkViewModel(get()) }
    viewModel { ProfileViewModel(get(), get()) }
}
