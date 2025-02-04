package com.example.zadumite_frontend.usecases
import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.domain.FetchWordUseCase
import com.example.zadumite_frontend.network.ZaDumiteApiService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.*

class FetchWordUseCaseTest {

    private lateinit var apiService: ZaDumiteApiService

    private lateinit var fetchWordUseCase: FetchWordUseCase

    @Before
    fun setUp() {
        apiService = mock()
        fetchWordUseCase = FetchWordUseCase(apiService)
    }

    @Test
    fun `invoke should return Word when API call is successful`() {
        runBlocking {
            val expectedWord = Word(word = "test", description = "example description", example = "exmaple example")
            whenever(apiService.getWordOfTheWeek()).thenReturn(expectedWord)

            val result = fetchWordUseCase()

            assertEquals(expectedWord, result)
            verify(apiService).getWordOfTheWeek()
        }
    }
}
