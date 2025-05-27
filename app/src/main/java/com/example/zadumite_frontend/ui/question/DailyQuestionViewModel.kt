package com.example.zadumite_frontend.ui.question

import androidx.lifecycle.ViewModel
import com.example.zadumite_frontend.data.model.question.Question
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.zadumite_frontend.data.model.question.UserAnswerRequest
import com.example.zadumite_frontend.data.model.question.UserQuestion
import com.example.zadumite_frontend.domain.FetchDailyQuestionUseCase
import com.example.zadumite_frontend.domain.GetUserUseCase
import com.example.zadumite_frontend.domain.SubmitUserAnswerUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.first


class DailyQuestionViewModel(
    private val fetchDailyQuestionUseCase: FetchDailyQuestionUseCase,
    private val submitUserAnswerUseCase: SubmitUserAnswerUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val dataStore: DataStore<Preferences>,
): ViewModel() {
    private val _questionState = mutableStateOf<Question?>(null)
    val question: State<Question?> = _questionState

    private val _submissionResult = mutableStateOf<UserQuestion?>(null)
    val submissionResult: State<UserQuestion?> = _submissionResult

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    private val key = stringPreferencesKey("last_daily_question_date")
    private val today = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    fun hasShownToday(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val prefs = dataStore.data.first()
                val lastShown = prefs[key]
                onResult(lastShown == today)
            } catch (e: Exception) {
                onResult(false)             }
        }
    }

    fun markAsShownToday() {
        viewModelScope.launch {
            try {
                dataStore.edit { prefs ->
                    prefs[key] = today
                }
            } catch (_: Exception) {}
        }
    }

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