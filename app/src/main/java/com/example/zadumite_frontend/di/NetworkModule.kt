package com.example.zadumite_frontend.di

import com.example.zadumite_frontend.di.providers.provideAccessOkHttpClient
import com.example.zadumite_frontend.di.providers.provideRefreshOkHttpClient
import com.example.zadumite_frontend.di.providers.provideRetrofit
import com.example.zadumite_frontend.di.qualifiers.AUTHENTICATED_CLIENT
import com.example.zadumite_frontend.di.qualifiers.TOKEN_REFRESH_CLIENT
import com.example.zadumite_frontend.network.ZaDumiteApiService
import com.example.zadumite_frontend.network.monitor.NetworkConnectivityObserver
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit


val networkModule = module {
    single(AUTHENTICATED_CLIENT) {
        provideAccessOkHttpClient(get(), get())
    }

    single(TOKEN_REFRESH_CLIENT) {
        provideRefreshOkHttpClient(get())
    }

    single<Retrofit> {
        provideRetrofit(get(AUTHENTICATED_CLIENT))
    }

    single(named("RefreshRetrofit")) {
        provideRetrofit(get(TOKEN_REFRESH_CLIENT))
    }

    single { NetworkConnectivityObserver(androidContext()) }

    single<ZaDumiteApiService> {
        get<Retrofit>().create(ZaDumiteApiService::class.java)
    }

}
