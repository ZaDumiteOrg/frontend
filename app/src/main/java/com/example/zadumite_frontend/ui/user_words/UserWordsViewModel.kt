package com.example.zadumite_frontend.ui.user_words

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.zadumite_frontend.data.model.word.Word
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.domain.FetchUserWordsUseCase
import kotlinx.coroutines.launch

class UserWordsViewModel(
    private val fetchUserWordsUseCase: FetchUserWordsUseCase
) : ViewModel() {

    private val _userWordsState = mutableStateOf<Result<List<Word>>?>(null)
    val userWordsState: State<Result<List<Word>>?> get() = _userWordsState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun fetchUserWords() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val words = fetchUserWordsUseCase()
                _userWordsState.value = words
            } catch (e: Exception) {
                _userWordsState.value = Result.failure(e)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
