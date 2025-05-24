package com.example.zadumite_frontend.usecases
import com.example.zadumite_frontend.data.model.token.JwtTokenManager
import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.domain.FetchUserWordsUseCase
import com.example.zadumite_frontend.data.api.ZaDumiteApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class FetchUserWordsUseCaseTest {

    private lateinit var apiService: ZaDumiteApiService
    private lateinit var tokenManager: JwtTokenManager

    private lateinit var fetchUserWordsUseCase: FetchUserWordsUseCase

    @Before
    fun setUp() {
        apiService = mock()
        tokenManager = mock()
        fetchUserWordsUseCase = FetchUserWordsUseCase(apiService, tokenManager)
    }

    @Test
    fun `invoke should return list of Words when userId is found`() {
        runBlocking {
            val userId = 1
            val expectedWords = listOf(
                Word(
                    word = "one",
                    description = "example description",
                    example = "exmaple example"
                ),
                Word(word = "two", description = "example description", example = "exmaple example")
            )

            whenever(tokenManager.getUserId()).thenReturn(userId)
            whenever(apiService.getUserWords(userId)).thenReturn(expectedWords)

            val result = fetchUserWordsUseCase()

            assertTrue(result.isSuccess)
            assertEquals(expectedWords, result.getOrNull())
            verify(tokenManager).getUserId()
            verify(apiService).getUserWords(userId)
        }
    }

    @Test
    fun `invoke should return failure when userId is null`() {
        runBlocking {
            whenever(tokenManager.getUserId()).thenReturn(null)

            val result = fetchUserWordsUseCase()

            assertTrue(result.isFailure)
            assertEquals("User ID not found", result.exceptionOrNull()?.message)
            verify(tokenManager).getUserId()
            verify(apiService, never()).getUserWords(any())
        }
    }
}
