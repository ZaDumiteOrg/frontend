package com.example.zadumite_frontend.usecases

import com.example.zadumite_frontend.data.model.token.TokenResponse
import com.example.zadumite_frontend.data.model.user.LogInRequest
import com.example.zadumite_frontend.data.repository.AuthRepository
import com.example.zadumite_frontend.domain.LogInUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.*
import org.junit.Before
import org.mockito.MockitoAnnotations
import org.mockito.Mock

class LogInUseCaseTest {

    @Mock
    private lateinit var authRepository: AuthRepository

    private lateinit var logInUseCase: LogInUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        logInUseCase = LogInUseCase(authRepository)
    }

    @Test
    fun `invoke should return TokenResponse when login is successful`() {
        runBlocking {
            val user = LogInRequest("test@example.com", "password123")
            val expectedResponse = TokenResponse("mocked_access_token", "mocked_refresh_token")
            `when`(authRepository.logIn(user)).thenReturn(expectedResponse)

            val result = logInUseCase(user)


            assertEquals(expectedResponse, result)
            verify(authRepository).logIn(user)
        }
    }
}
