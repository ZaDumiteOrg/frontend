package com.example.zadumite_frontend.ui.login

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.data.model.token.TokenResponse
import com.example.zadumite_frontend.data.model.user.LogInRequest
import com.example.zadumite_frontend.domain.LogInUseCase
import com.example.zadumite_frontend.utils.token.TokenUtils
import kotlinx.coroutines.launch

class LogInViewModel(private val logInUseCase: LogInUseCase): ViewModel() {
    private val _loginState = mutableStateOf<Result<TokenResponse>?>(null)
    val loginState: State<Result<TokenResponse>?> get() = _loginState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun logIn(email: String, password: String, context: Context, onResult: (String?) -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val loginRequest = LogInRequest(email, password)
                val tokenResponse = logInUseCase(loginRequest)

                val role = TokenUtils.decodeUserRoleFromToken(tokenResponse.accessToken)

                if (role != null) {
                    _loginState.value = Result.success(tokenResponse)
                } else {
                    _loginState.value = Result.failure(Exception(context.getString(R.string.failed_decode_role)))
                }
                onResult(role)
            }catch(e: Exception) {
                _isLoading.value = false
                _loginState.value = Result.failure(Exception(context.getString(R.string.login_failed)))
                onResult(null)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
