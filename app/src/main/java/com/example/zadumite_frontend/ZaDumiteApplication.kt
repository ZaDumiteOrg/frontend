package com.example.zadumite_frontend

import android.app.Application
import android.util.Log
import com.example.zadumite_frontend.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
class ZaDumiteApplication : Application()  {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ZaDumiteApplication)
            modules(appModule)
        }
    }
}