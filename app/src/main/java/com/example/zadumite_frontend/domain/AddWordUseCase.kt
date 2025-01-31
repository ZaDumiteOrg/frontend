package com.example.zadumite_frontend.domain

import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.data.repository.WordRepository

class AddWordUseCase(private val wordRepository: WordRepository) {
    suspend operator fun invoke(
        word: String,
        example: String,
        description: String,
        synonym: String? = null
    ): Result<Word> {
        val wordRequest = Word(word, example, description, synonym)
        return wordRepository.addWord(wordRequest)
    }
}