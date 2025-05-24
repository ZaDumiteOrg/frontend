package com.example.zadumite_frontend.usecases

import com.example.zadumite_frontend.data.model.user.User
import com.example.zadumite_frontend.data.repository.DailyQuestionRepository
import com.example.zadumite_frontend.domain.GetUserScoreUseCase
import com.example.zadumite_frontend.domain.GetUserUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class GetUserScoreUseCaseTest {

    private lateinit var repository: DailyQuestionRepository
    private lateinit var getUserUseCase: GetUserUseCase
    private lateinit var useCase: GetUserScoreUseCase

    @Before
    fun setUp() {
        repository = mock()
        getUserUseCase = mock()
        useCase = GetUserScoreUseCase(repository, getUserUseCase)
    }

    @Test
    fun `invoke should return user score when user is found`() {
        runBlocking {
            val user = User(
                firstName = "John",
                lastName = "Doe",
                email = "john@example.com",
                password = "password123",
                role = "user",
                id = 1
            )
            val score = 15
            val expectedResult = Result.success(score)

            whenever(getUserUseCase()).thenReturn(user)
            whenever(repository.getUserScore(user.id)).thenReturn(expectedResult)

            val result = useCase()

            assertTrue(result.isSuccess)
            assertEquals(score, result.getOrNull())
            verify(getUserUseCase).invoke()
            verify(repository).getUserScore(user.id)
        }
    }

    @Test
    fun `invoke should return failure when getUserUseCase throws`() {
        runBlocking {
            val exception = RuntimeException("User not logged in")

            whenever(getUserUseCase()).thenThrow(exception)

            val result = useCase()

            assertTrue(result.isFailure)
            assertEquals("User not logged in", result.exceptionOrNull()?.message)
            verify(getUserUseCase).invoke()
            verify(repository, never()).getUserScore(any())
        }
    }
}