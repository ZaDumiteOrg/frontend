package com.example.zadumite_frontend.domain

import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.data.api.ZaDumiteApiService

class FetchWordUseCase(private val apiService : ZaDumiteApiService) {
    suspend operator fun invoke(): Word {
        return apiService.getWordOfTheWeek()
    }
}