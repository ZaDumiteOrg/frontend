package com.example.zadumite_frontend.ui.profile

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.data.model.user.User
import com.example.zadumite_frontend.domain.GetUserUseCase
import com.example.zadumite_frontend.domain.LogoutUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val getUserUseCase: GetUserUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _profileState = MutableLiveData<User?>(null)
    val profileState: LiveData<User?> = _profileState

    private val _logoutState = MutableLiveData<Boolean>()

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _errorMessage = MutableLiveData<String?>(null)
    val errorMessage: LiveData<String?> = _errorMessage

    fun fetchUserDetails(context: Context) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val user = getUserUseCase()
                _profileState.value = user
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
                _logoutState.postValue(true)
            } catch (e: Exception) {
                _isLoading.value = false
                _logoutState.postValue(false)
            }
        }
    }
}