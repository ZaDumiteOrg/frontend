package com.example.zadumite_frontend.ui.login

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

    fun logIn(email: String, password: String) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val loginRequest = LogInRequest(email, password)
                val tokenResponse = repository.logIn(loginRequest)
                _loginResult.postValue(Result.success(tokenResponse))
            } catch (e: Exception) {
                _loginResult.postValue(Result.failure(e))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}