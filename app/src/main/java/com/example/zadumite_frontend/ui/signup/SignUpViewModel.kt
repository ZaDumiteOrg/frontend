package com.example.zadumite_frontend.ui.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.data.model.user.SignUpRequest
import com.example.zadumite_frontend.data.model.user.SignUpResponse
import com.example.zadumite_frontend.data.repository.AuthRepository
import kotlinx.coroutines.launch

class SignUpViewModel(private val repository: AuthRepository) : ViewModel() {
    private val _signUpState = MutableLiveData<Result<SignUpResponse>>()
    val signUpState: LiveData<Result<SignUpResponse>> get() = _signUpState
    fun signUp(request: SignUpRequest) {
        viewModelScope.launch {
            try {
                val response = repository.signUp(request)
                _signUpState.value = Result.success(response)
            } catch (e: Exception) {
                _signUpState.value = Result.failure(e)
            }
        }
    }
}
