package com.example.zadumite_frontend.ui.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.data.model.token.TokenResponse
import com.example.zadumite_frontend.data.model.user.LogInRequest
import com.example.zadumite_frontend.domain.LogInUseCase
import com.example.zadumite_frontend.utils.token.TokenUtils
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LogInViewModel(private val logInUseCase: LogInUseCase): ViewModel() {
    private val _loginState = MutableLiveData<Result<TokenResponse>>()
    val loginState: LiveData<Result<TokenResponse>> = _loginState

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun logIn(email: String, password: String, context: Context, onResult: (String?) -> Unit) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val loginRequest = LogInRequest(email, password)
                val tokenResponse = logInUseCase(loginRequest)

                val role = TokenUtils.decodeUserRoleFromToken(tokenResponse.accessToken)

                if (role != null) {
                    _loginState.postValue(Result.success(tokenResponse))
                } else {
                    _loginState.postValue(Result.failure(Exception("Failed to decode role")))
                }

                onResult(role)
            }catch (e: HttpException) {
                _isLoading.postValue(false)
                val error = when (e.code()) {
                    401 -> context.getString(R.string.error_invalid_credentials)
                    500 -> context.getString(R.string.error_server)
                    else -> context.getString(R.string.login_failed)
                }
                _loginState.postValue(Result.failure(Exception(error)))
            } catch (e: Exception) {
                _isLoading.postValue(false)
                _loginState.postValue(Result.failure(Exception(context.getString(R.string.error_unexpected))))
                onResult(null)
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}
