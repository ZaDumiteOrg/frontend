package com.example.zadumite_frontend.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.zadumite_frontend.data.repository.AuthRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.data.model.token.TokenResponse
import com.example.zadumite_frontend.data.model.user.LogInRequest
import kotlinx.coroutines.launch

class LogInViewModel(private val repository: AuthRepository): ViewModel() {
    private val _loginResult = MutableLiveData<Result<TokenResponse>>()
    val loginResult: LiveData<Result<TokenResponse>> = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun logIn(email: String, password: String, onResult: (Int?) -> Unit) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val loginRequest = LogInRequest(email, password)
                val tokenResponse = repository.logIn(loginRequest)

                val userId = decodeUserIdFromToken(tokenResponse.accessToken)

                if (userId != null) {
                    _loginResult.postValue(Result.success(tokenResponse))
                } else {
                    _loginResult.postValue(Result.failure(Exception("Failed to decode user ID")))
                }

                onResult(userId)
            } catch (e: Exception) {
                _loginResult.postValue(Result.failure(e))
                onResult(null)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }

    private fun decodeUserIdFromToken(token: String): Int? {
        return try {
            val parts = token.split(".")
            if (parts.size == 3) {
                val payload = parts[1]
                val paddedPayload = when (payload.length % 4) {
                    2 -> "$payload=="
                    3 -> "$payload="
                    else -> payload
                }
                val decodedPayload = String(android.util.Base64.decode(paddedPayload, android.util.Base64.URL_SAFE or android.util.Base64.NO_WRAP))
                val json = org.json.JSONObject(decodedPayload)
                json.optInt("sub", -1).takeIf { it != -1 }
            } else {
                null
            }
        } catch (e: Exception) {
            Log.e("TokenDecode", "Failed to decode token: ${e.message}")
            null
        }
    }

}
