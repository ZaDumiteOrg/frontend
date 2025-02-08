package com.example.zadumite_frontend.usecases

import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.data.repository.WordRepository
import com.example.zadumite_frontend.domain.AddWordUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class AddWordUseCaseTest {

    private lateinit var wordRepository: WordRepository

    private lateinit var addWordUseCase: AddWordUseCase

    @Before
    fun setUp() {
        wordRepository = mock()
        addWordUseCase = AddWordUseCase(wordRepository)
    }

    @Test
    fun `invoke should return Result success when word is added successfully`() {
        runBlocking {
            val word = "Example"
            val description = "This is an example word."
            val example = "Here is an example usage."
            val synonym = "Sample"

            val expectedWord = Word(word, description, example, synonym)
            val expectedResult: Result<Word> = Result.success(expectedWord)

            whenever(wordRepository.addWord(any())).thenReturn(expectedResult)

            val result = addWordUseCase(word, description, example, synonym)

            assertEquals(expectedResult, result)
            verify(wordRepository).addWord(any())
        }
    }

    @Test
    fun `invoke should return Result failure when repository fails`() {
        runBlocking {
            val word = "Failure"
            val description = "This will fail."
            val example = "Failure example."
            val synonym = null

            val expectedException = Exception("Failed to add word")
            val expectedResult: Result<Word> = Result.failure(expectedException)

            whenever(wordRepository.addWord(any())).thenReturn(expectedResult)

            val result = addWordUseCase(word, description, example, synonym)

            assertEquals(expectedResult, result)
            verify(wordRepository).addWord(any())
        }
    }
}
