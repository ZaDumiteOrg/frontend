package com.example.zadumite_frontend.ui.signup

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.data.model.user.SignUpRequest
import com.example.zadumite_frontend.data.model.user.SignUpResponse
import com.example.zadumite_frontend.domain.SignUpUseCase
import com.example.zadumite_frontend.utils.token.TokenUtils
import kotlinx.coroutines.launch

class SignUpViewModel(private val signUpUseCase: SignUpUseCase) : ViewModel() {

    private val _signUpState = mutableStateOf<Result<SignUpResponse>?>(null)
    val signUpState: State<Result<SignUpResponse>?> get() = _signUpState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun signUp(request: SignUpRequest, context: Context, onResult: (Int?) -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val response = signUpUseCase(request)

                val userId = response.accessToken.let { token ->
                    TokenUtils.decodeUserIdFromToken(token)
                }

                if (userId != null) {
                    _signUpState.value = Result.success(response)
                } else {
                    _signUpState.value = Result.failure(Exception(context.getString(R.string.failed_decode_id)))
                }

                onResult(userId)
            } catch (e: Exception) {
                _signUpState.value = Result.failure(e)
                onResult(null)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
