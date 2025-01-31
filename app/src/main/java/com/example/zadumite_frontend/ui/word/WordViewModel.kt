package com.example.zadumite_frontend.ui.word

import android.util.Log
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

    private val _loading = mutableStateOf(false)
    val loading: State<Boolean> = _loading

    init {
        fetchWordOfTheWeek()
    }

    private fun fetchWordOfTheWeek() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val word = fetchWordUseCase()
                _wordOfTheWeek.value = word
            } catch (e: Exception) {
                Log.e("WordViewModel", "Error fetching word: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }
}
