package com.example.zadumite_frontend.ui.word

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.network.monitor.ConnectivityObserver
import com.example.zadumite_frontend.network.monitor.NetworkViewModel
import com.example.zadumite_frontend.ui.custom_elements.CustomProgressIndicator
import com.example.zadumite_frontend.ui.question.DailyQuestionPopup
import com.example.zadumite_frontend.ui.question.DailyQuestionViewModel
import com.example.zadumite_frontend.ui.theme.errorMessageStyle
import org.koin.androidx.compose.koinViewModel
import java.util.Calendar

@Composable
fun WordOfTheWeekScreen(
    viewModel: WordViewModel = koinViewModel(),
    networkViewModel: NetworkViewModel = koinViewModel(),
    dailyQuestionViewModel: DailyQuestionViewModel = koinViewModel()
) {

    val word by viewModel.wordOfTheWeek
    val isLoading by viewModel.isLoading
    val networkStatus by networkViewModel.networkStatus.collectAsState()
    val isNetworkAvailable = networkStatus == ConnectivityObserver.Status.Available

    val question by dailyQuestionViewModel.question
    val error by dailyQuestionViewModel.error
    val context = LocalContext.current
    val hasShownToday = remember { mutableStateOf(false) }

    LaunchedEffect(isNetworkAvailable) {
        if (isNetworkAvailable) {
            val now = Calendar.getInstance()
            val currentHour = now.get(Calendar.HOUR_OF_DAY)

            if (currentHour >= 10 && !hasShownToday.value) {
                dailyQuestionViewModel.loadDailyQuestion()
                hasShownToday.value = true
            }
        }
    }

    if (question != null && !hasShownToday.value) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        ) {
            DailyQuestionPopup(
                dailyQuestion = question!!,
                onDismiss = { hasShownToday.value = true },
                onAnswerSubmit = { selected ->
                    dailyQuestionViewModel.submitAnswer(selected)
                    hasShownToday.value = true
                }
            )
        }
    }

    LaunchedEffect(error) {
        error?.let {
            val errorMessage = context.getString(R.string.error_string_added, it)
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            !isNetworkAvailable -> {
                Text(
                    text = stringResource(R.string.no_internet_connection),
                    style = errorMessageStyle,
                    textAlign = TextAlign.Center
                )
            }
            isLoading -> {
                CustomProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            }
            word != null -> {
                WordCard(word = word!!)
            }
            else -> {
                Text(
                    text = stringResource(R.string.fetching_word_failed),
                    style = errorMessageStyle)
            }
        }
    }
}