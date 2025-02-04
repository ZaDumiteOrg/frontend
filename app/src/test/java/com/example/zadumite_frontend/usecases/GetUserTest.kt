package com.example.zadumite_frontend.usecases
import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import com.example.zadumite_frontend.data.model.user.User
import com.example.zadumite_frontend.data.repository.UserRepository
import com.example.zadumite_frontend.domain.GetUserUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class GetUserUseCaseTest {

    // Mock dependencies
    private lateinit var userRepository: UserRepository
    private lateinit var tokenManager: JwtTokenManager

    // System under test
    private lateinit var getUserUseCase: GetUserUseCase

    @Before
    fun setUp() {
        userRepository = mock()
        tokenManager = mock()
        getUserUseCase = GetUserUseCase(userRepository, tokenManager)
    }

    @Test
    fun `invoke should return User when userId is found`() {
        runBlocking {
            val userId = 1
            val expectedUser = User(
                id = userId,
                email = "test@example.com",
                firstName = "Test User",
                password = "Pass123456",
                lastName = "Test User",
                role = "user"
            )

            whenever(tokenManager.getUserId()).thenReturn(userId)
            whenever(userRepository.getUserById(userId)).thenReturn(expectedUser)

            val result = getUserUseCase()

            assertEquals(expectedUser, result)
            verify(tokenManager).getUserId()
            verify(userRepository).getUserById(userId)
        }
    }

    @Test
    fun `invoke should throw Exception when userId is null`() {
        runBlocking {
            whenever(tokenManager.getUserId()).thenReturn(null)

            val exception = assertThrows(Exception::class.java) {
                runBlocking { getUserUseCase() }
            }

            assertEquals("User ID not found", exception.message)
            verify(tokenManager).getUserId()
            verify(userRepository, never()).getUserById(any())
        }
    }
}

