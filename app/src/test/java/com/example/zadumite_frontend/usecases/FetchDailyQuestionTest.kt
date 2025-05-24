package com.example.zadumite_frontend.usecases

import com.example.zadumite_frontend.domain.FetchDailyQuestionUseCase
import com.example.zadumite_frontend.data.model.question.Question
import com.example.zadumite_frontend.data.repository.DailyQuestionRepository
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class FetchDailyQuestionUseCaseTest {

    private lateinit var repository: DailyQuestionRepository
    private lateinit var useCase: FetchDailyQuestionUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = FetchDailyQuestionUseCase(repository)
    }

    @Test
    fun `invoke should return success with daily question`() {
        runBlocking {
            val question = Question(
                id = 1,
                questionText = "What is a metaphor?",
                optionA = "A comparison without like or as",
                optionB = "A figure of speech using 'like'",
                optionC = "A factual statement",
                optionD = "A type of irony",
                correctOption = "A"
            )
            val expectedResult = Result.success(question)

            whenever(repository.getDailyQuestion()).thenReturn(expectedResult)

            val result = useCase()

            assertTrue(result.isSuccess)
            assertEquals(question, result.getOrNull())
            verify(repository).getDailyQuestion()
        }
    }

    @Test
    fun `invoke should return failure when repository fails`() {
        runBlocking {
            val exception = RuntimeException("Server error")
            val expectedResult = Result.failure<Question>(exception)

            whenever(repository.getDailyQuestion()).thenReturn(expectedResult)

            val result = useCase()

            assertTrue(result.isFailure)
            assertEquals("Server error", result.exceptionOrNull()?.message)
            verify(repository).getDailyQuestion()
        }
    }
}
