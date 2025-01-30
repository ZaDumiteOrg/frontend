package com.example.zadumite_frontend.ui.login

import androidx.lifecycle.ViewModel
import com.example.zadumite_frontend.data.repository.AuthRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.data.model.token.TokenResponse
import com.example.zadumite_frontend.data.model.user.LogInRequest
import com.example.zadumite_frontend.utils.token.TokenUtils
import kotlinx.coroutines.launch

class LogInViewModel(private val repository: AuthRepository): ViewModel() {
    private val _loginResult = MutableLiveData<Result<TokenResponse>>()
    val loginResult: LiveData<Result<TokenResponse>> = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun logIn(email: String, password: String, onResult: (String?) -> Unit) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val loginRequest = LogInRequest(email, password)
                val tokenResponse = repository.logIn(loginRequest)

              //val userId = TokenUtils.decodeUserIdFromToken(tokenResponse.accessToken)
                val role = TokenUtils.decodeUserRoleFromToken(tokenResponse.accessToken)

                if (role != null) {
                    _loginResult.postValue(Result.success(tokenResponse))
                } else {
                    _loginResult.postValue(Result.failure(Exception("Failed to decode role")))
                }

                onResult(role)
            } catch (e: Exception) {
                _loginResult.postValue(Result.failure(e))
                onResult(null)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
