package com.example.zadumite_frontend.ui.user_words

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.network.ZaDumiteApiService
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class UserWordsViewModel(private val apiService: ZaDumiteApiService) : ViewModel() {
    private val _userWords = mutableStateOf<List<Word>>(emptyList())
    val userWords: State<List<Word>> = _userWords

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    fun fetchUserWords(userId: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                Log.d("UserWordsViewModel", "Fetching words for user: $userId")
                val words = apiService.getUserWords(userId)
                _userWords.value = words
            } catch (e: Exception) {
                Log.e("UserWordsViewModel", "Error fetching user words: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }
}
