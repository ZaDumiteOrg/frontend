package com.example.zadumite_frontend.ui.question

import androidx.lifecycle.ViewModel
import com.example.zadumite_frontend.data.model.question.Question
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import com.example.zadumite_frontend.data.model.question.UserAnswerRequest
import com.example.zadumite_frontend.data.model.question.UserQuestion
import com.example.zadumite_frontend.domain.FetchDailyQuestionUseCase
import com.example.zadumite_frontend.domain.GetUserUseCase
import com.example.zadumite_frontend.domain.SubmitUserAnswerUseCase

class DailyQuestionViewModel(
    private val fetchDailyQuestionUseCase: FetchDailyQuestionUseCase,
    private val submitUserAnswerUseCase: SubmitUserAnswerUseCase,
    private val getUserUseCase: GetUserUseCase,
): ViewModel() {
    private val _questionState = mutableStateOf<Question?>(null)
    val question: State<Question?> = _questionState

    private val _submissionResult = mutableStateOf<UserQuestion?>(null)
    val submissionResult: State<UserQuestion?> = _submissionResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun loadDailyQuestion() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = fetchDailyQuestionUseCase()
            result
                .onSuccess {
                    _questionState.value = it
                    _error.value = null
                }
                .onFailure {
                    _error.value = it.message ?: "Unknown error"
                }
            _isLoading.value = false
        }
    }

    fun submitAnswer(selectedOption: String) {
        val q = _questionState.value ?: return
        _isLoading.value = true
        viewModelScope.launch {
            try {
                val user = getUserUseCase()
                val request = UserAnswerRequest(user.id, q.id, selectedOption)
                val result = submitUserAnswerUseCase(request)
                result
                    .onSuccess {
                        _submissionResult.value = it
                        _error.value = null
                    }
                    .onFailure {
                        _error.value = it.message
                    }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}