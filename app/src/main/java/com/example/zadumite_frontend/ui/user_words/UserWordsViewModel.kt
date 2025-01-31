package com.example.zadumite_frontend.ui.user_words

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.zadumite_frontend.data.model.word.Word
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zadumite_frontend.domain.FetchUserWordsUseCase
import kotlinx.coroutines.launch


class UserWordsViewModel(
    private val fetchUserWordsUseCase: FetchUserWordsUseCase
) : ViewModel() {
    private val _userWords = MutableLiveData<List<Word>>()
    val userWords: LiveData<List<Word>> get() = _userWords

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    fun fetchUserWords() {
        viewModelScope.launch {
            _loading.value = true
            try {
                val words = fetchUserWordsUseCase()
                _userWords.value = words
            } catch (e: Exception) {
                Log.e("UserWordsViewModel", "Error fetching user words: ${e.message}")
            } finally {
                _loading.value = false
            }
        }
    }
}
