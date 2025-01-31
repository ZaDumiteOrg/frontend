package com.example.zadumite_frontend.ui.add_word

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.domain.AddWordUseCase
import kotlinx.coroutines.launch

class AddWordViewModel(private val addWordUseCase: AddWordUseCase) : ViewModel() {
    private val _addWordResult = MutableLiveData<Result<Word>>()
    val addWordResult: LiveData<Result<Word>> = _addWordResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
    fun addWord(word: String, definition: String, example: String,  synonym: String? = null) {
        _isLoading.postValue(true)
        viewModelScope.launch {
            try {
                val wordResponse =  addWordUseCase(word, definition, example,  synonym)
                _addWordResult.postValue(wordResponse)

            } catch (e: Exception) {
                _addWordResult.postValue(Result.failure(e))
            } finally {
                _isLoading.postValue(false)
            }
        }
    }
}