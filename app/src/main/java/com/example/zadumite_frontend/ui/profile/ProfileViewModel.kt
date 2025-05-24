package com.example.zadumite_frontend.ui.profile

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.data.model.user.User
import com.example.zadumite_frontend.domain.GetUserUseCase
import com.example.zadumite_frontend.domain.LogoutUseCase
import kotlinx.coroutines.launch
import androidx.compose.runtime.State
import com.example.zadumite_frontend.domain.GetUserScoreUseCase


class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val getUserScoreUseCase: GetUserScoreUseCase
) : ViewModel() {
    private val _profileState = mutableStateOf<User?>(null)
    val profileState: State<User?> = _profileState

    private val _logoutState = mutableStateOf(false)

    private val _userScore = mutableStateOf<Int?>(null)
    val userScore: State<Int?> = _userScore

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: State<String?> = _errorMessage

    fun fetchUserDetails(context: Context) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val user = getUserUseCase()
                _profileState.value = user

                val scoreResult = getUserScoreUseCase()
                scoreResult
                    .onSuccess { score ->
                        _userScore.value = score
                    }
                    .onFailure {
                        _errorMessage.value = context.getString(R.string.failed_fetching_score)
                    }
            } catch (e: Exception) {
                _errorMessage.value = context.getString(R.string.failed_fetching_user)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun logout(onLogoutSuccess: () -> Unit) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                logoutUseCase()
                onLogoutSuccess()
                _logoutState.value = true
            } catch (e: Exception) {
                _isLoading.value = false
                _logoutState.value = false
            }
        }
    }
}