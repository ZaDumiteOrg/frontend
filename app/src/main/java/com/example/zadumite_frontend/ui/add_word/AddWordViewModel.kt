package com.example.zadumite_frontend.ui.add_word

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.domain.AddWordUseCase
import kotlinx.coroutines.launch

class AddWordViewModel(private val addWordUseCase: AddWordUseCase) : ViewModel() {
    private val _addWordState = mutableStateOf<Result<Word>?>(null)
    val addWordState: State<Result<Word>?> = _addWordState


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
}