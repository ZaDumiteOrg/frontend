package com.example.zadumite_frontend.usecases

import com.example.zadumite_frontend.data.model.question.UserAnswerRequest
import com.example.zadumite_frontend.data.model.question.UserQuestion
import com.example.zadumite_frontend.data.repository.DailyQuestionRepository
import com.example.zadumite_frontend.domain.SubmitUserAnswerUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*
import java.util.Date

class SubmitUserAnswerUseCaseTest {

    private lateinit var repository: DailyQuestionRepository
    private lateinit var useCase: SubmitUserAnswerUseCase

    @Before
    fun setUp() {
        repository = mock()
        useCase = SubmitUserAnswerUseCase(repository)
    }

    @Test
    fun `invoke should return success when repository accepts answer`() {
        runBlocking {
            val request = UserAnswerRequest(
                userId = 1,
                questionId = 42,
                selectedOption = "B"
            )
            val now = Date()
            val expectedResponse = UserQuestion(
                id = 42,
                selectedOption = "B",
                isCorrect = true,
                answeredAt = now
            )
            val expectedResult = Result.success(expectedResponse)

            whenever(repository.submitUserAnswer(request)).thenReturn(expectedResult)

            val result = useCase(request)

            assertTrue(result.isSuccess)
            assertEquals(expectedResponse, result.getOrNull())
            verify(repository).submitUserAnswer(request)
        }
    }

    @Test
    fun `invoke should return failure when repository fails`() {
        runBlocking {
            val request = UserAnswerRequest(
                userId = 1,
                questionId = 42,
                selectedOption = "B"
            )
            val exception = RuntimeException("Submission failed")
            val expectedResult = Result.failure<UserQuestion>(exception)

            whenever(repository.submitUserAnswer(request)).thenReturn(expectedResult)

            val result = useCase(request)

            assertTrue(result.isFailure)
            assertEquals("Submission failed", result.exceptionOrNull()?.message)
            verify(repository).submitUserAnswer(request)
        }
    }
}