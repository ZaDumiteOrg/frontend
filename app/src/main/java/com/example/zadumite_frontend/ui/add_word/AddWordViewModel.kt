package com.example.zadumite_frontend.ui.add_word

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.domain.AddWordUseCase
import com.example.zadumite_frontend.domain.LogoutUseCase
import kotlinx.coroutines.launch

class AddWordViewModel(
    private val addWordUseCase: AddWordUseCase,
    private val logoutUseCase: LogoutUseCase
) : ViewModel() {
    private val _addWordState = mutableStateOf<Result<Word>?>(null)
    val addWordState: State<Result<Word>?> = _addWordState

    private val _logoutState = mutableStateOf(false)

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading
    fun addWord(word: String, definition: String, example: String,  synonym: String? = null) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val wordResponse =  addWordUseCase(word, definition, example,  synonym)
                _addWordState.value = wordResponse

            } catch (e: Exception) {
                _addWordState.value = Result.failure(e)
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