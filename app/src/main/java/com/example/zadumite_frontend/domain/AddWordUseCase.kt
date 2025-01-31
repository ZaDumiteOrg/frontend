package com.example.zadumite_frontend.domain

import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.data.repository.WordRepository

class AddWordUseCase(private val wordRepository: WordRepository) {
    suspend operator fun invoke(
        word: String,
        description: String,
        example: String,
        synonym: String? = null
    ): Result<Word> {
        val wordRequest = Word(word, description, example, synonym)
        return wordRepository.addWord(wordRequest)
    }
}