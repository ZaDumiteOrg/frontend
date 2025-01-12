package com.example.zadumite_frontend.di

import com.example.zadumite_frontend.network.ZaDumiteApiService
import com.example.zadumite_frontend.network.authenticator.AuthAuthenticator
import com.example.zadumite_frontend.network.interception.AccessTokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single {
        println("Creating OkHttpClient...")
        OkHttpClient.Builder()
            .addInterceptor(get<AccessTokenInterceptor>())
            .authenticator(get<AuthAuthenticator>())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        println("Creating Retrofit instance...")
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .build()
    }

    single {
        println("Creating ZaDumiteApiService...")
        get<Retrofit>().create(ZaDumiteApiService::class.java)
    }
}

