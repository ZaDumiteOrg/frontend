package com.example.zadumite_frontend.ui.word

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.zadumite_frontend.data.model.word.Word
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.domain.FetchWordUseCase
import kotlinx.coroutines.launch


class WordViewModel(private val fetchWordUseCase: FetchWordUseCase) : ViewModel() {
    private val _wordOfTheWeek = mutableStateOf<Word?>(null)
    val wordOfTheWeek: State<Word?> = _wordOfTheWeek

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        fetchWordOfTheWeek()
    }

    private fun fetchWordOfTheWeek() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val word = fetchWordUseCase()
                _wordOfTheWeek.value = word
            } catch (e: Exception) {
                throw Exception("Error fetching word: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }
}
