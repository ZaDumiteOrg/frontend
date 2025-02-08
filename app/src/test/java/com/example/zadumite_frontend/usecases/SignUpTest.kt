package com.example.zadumite_frontend.usecases

import com.example.zadumite_frontend.data.model.user.SignUpRequest
import com.example.zadumite_frontend.data.model.user.SignUpResponse
import com.example.zadumite_frontend.data.model.user.User
import com.example.zadumite_frontend.data.repository.AuthRepository
import com.example.zadumite_frontend.domain.SignUpUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock

class SignUpUseCaseTest {

    private lateinit var authRepository: AuthRepository

    private lateinit var signUpUseCase: SignUpUseCase

    @Before
    fun setUp() {
        authRepository = mock()
        signUpUseCase = SignUpUseCase(authRepository)
    }

    @Test
    fun `invoke should return SignUpResponse when signUp is successful`() {
        runBlocking {
            val user = User("Name", "Lastname", "email@gmail.com", "Pass123456", "user", 1)
            val request = SignUpRequest("Name", "Lastname", "email@gmail.com", "Pass123456")
            val expectedResponse = SignUpResponse(user, "mock_access_token", "mock_refresh_token")

            `when`(authRepository.signUp(request)).thenReturn(expectedResponse)

            val result = signUpUseCase(request)

            assertEquals(expectedResponse, result)
            verify(authRepository).signUp(request)
        }
    }
}
