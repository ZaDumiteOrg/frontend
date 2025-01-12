package com.example.zadumite_frontend.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.zadumite_frontend.data.model.datastore.JwtTokenDataStore
import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module

private const val AUTH_PREFERENCES = "auth_preferences"

val jwtTokenModule = module {
    single<JwtTokenManager> { JwtTokenDataStore(get()) }
    single<DataStore<Preferences>> { provideDataStore(get()) }
}

fun provideDataStore(context: Context): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create(
        corruptionHandler = ReplaceFileCorruptionHandler(
            produceNewData = { emptyPreferences() }
        ),
        produceFile = { context.preferencesDataStoreFile(AUTH_PREFERENCES) },
        scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    )
}
