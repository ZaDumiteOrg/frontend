package com.example.zadumite_frontend.data.model.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class JwtTokenDataStore(
    private val dataStore: DataStore<Preferences>
) : JwtTokenManager {

    companion object {
        val ACCESS_JWT_KEY = stringPreferencesKey("access_jwt")
        val REFRESH_JWT_KEY = stringPreferencesKey("refresh_jwt")
        val USER_ID_KEY = intPreferencesKey("user_id")
    }

    override suspend fun saveUserId(userId: Int) {
        dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = userId
        }
    }

    override suspend fun getUserId(): Int? {
        return dataStore.data.map { preferences ->
            preferences[USER_ID_KEY]
        }.firstOrNull()
    }

    override suspend fun saveAccessJwt(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_JWT_KEY] = accessToken
        }
    }

    override suspend fun saveRefreshJwt(refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[REFRESH_JWT_KEY] = refreshToken
        }
    }

    override suspend fun getAccessJwt(): String? {
        return dataStore.data.map { preferences ->
            preferences[ACCESS_JWT_KEY]
        }.first()
    }

    override suspend fun getRefreshJwt(): String? {
        return dataStore.data.map { preferences ->
            preferences[REFRESH_JWT_KEY]
        }.first()
    }

    override suspend fun clearAllTokens() {
        dataStore.edit { preferences ->
            preferences.remove(ACCESS_JWT_KEY)
            preferences.remove(REFRESH_JWT_KEY)
            preferences.remove(USER_ID_KEY)
        }
    }
}