package com.example.zadumite_frontend.ui.user_words

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.network.ZaDumiteApiService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import kotlinx.coroutines.launch


class UserWordsViewModel(
    private val apiService: ZaDumiteApiService,
    private val userManager: JwtTokenManager
) : ViewModel() {
    private val _userWords = MutableLiveData<List<Word>>()
    val userWords: LiveData<List<Word>> get() = _userWords

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    private val _userId = MutableLiveData<String?>()
    val userId: LiveData<String?> get() = _userId

    fun fetchUserWords() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val userId = userManager.getUserId()
                if(userId != null) {
                    val words = apiService.getUserWords(userId)
                    _userWords.value = words
                }
            } catch (e: Exception) {
                Log.e("UserWordsViewModel", "Error fetching user words: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }
}
