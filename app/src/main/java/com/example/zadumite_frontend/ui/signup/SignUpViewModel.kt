package com.example.zadumite_frontend.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.data.model.user.SignUpRequest
import com.example.zadumite_frontend.data.model.user.SignUpResponse
import com.example.zadumite_frontend.domain.SignUpUseCase
import com.example.zadumite_frontend.utils.token.TokenUtils
import kotlinx.coroutines.launch

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {
    private val _signUpState = MutableLiveData<Result<SignUpResponse>>()
    val signUpState: LiveData<Result<SignUpResponse>> get() = _signUpState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun signUp(request: SignUpRequest, onResult: (Int?) -> Unit) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val response = signUpUseCase(request)

                val userId = response.accessToken.let { token ->
                    TokenUtils.decodeUserIdFromToken(token)
                }

                if (userId != null) {
                    _signUpState.postValue(Result.success(response))
                } else {
                    _signUpState.postValue(Result.failure(Exception("Failed to decode user ID")))
                }

                onResult(userId)
            } catch (e: Exception) {
                _signUpState.postValue(Result.failure(e))
                onResult(null)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
