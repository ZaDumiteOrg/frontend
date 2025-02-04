package com.example.zadumite_frontend.usecases

import com.example.zadumite_frontend.data.repository.UserRepository
import com.example.zadumite_frontend.domain.LogoutUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.verify

class LogoutUseCaseTest {

    private lateinit var userRepository: UserRepository

    private lateinit var logoutUseCase: LogoutUseCase

    @Before
    fun setUp() {
        userRepository = mock(UserRepository::class.java)
        logoutUseCase = LogoutUseCase(userRepository)
    }

    @Test
    fun `invoke should call userRepository logout`() = runBlocking {
        logoutUseCase()

        verify(userRepository).logout()
    }
}
