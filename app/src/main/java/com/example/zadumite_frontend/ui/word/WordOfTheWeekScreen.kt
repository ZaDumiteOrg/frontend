package com.example.zadumite_frontend.ui.word

import android.widget.Toast
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
import com.example.zadumite_frontend.ui.question.DailyQuestionViewModel
import com.example.zadumite_frontend.ui.theme.errorMessageStyle
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import com.example.zadumite_frontend.ui.question.QuestionOption
import com.example.zadumite_frontend.ui.theme.Beige
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.LightBrown
import com.example.zadumite_frontend.ui.theme.questionHeader
import com.example.zadumite_frontend.ui.theme.questionTextStyle
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
    val hasChecked = remember { mutableStateOf(false) }

    LaunchedEffect(isNetworkAvailable) {
        if (isNetworkAvailable && !hasChecked.value) {
            val now = Calendar.getInstance()
            val currentHour = now.get(Calendar.HOUR_OF_DAY)

            dailyQuestionViewModel.hasShownToday { wasShown ->
                if (!wasShown && currentHour >= 10) {
                    dailyQuestionViewModel.loadDailyQuestion()
                }
                hasShownToday.value = wasShown
                hasChecked.value = true
            }
        }
    }

    if (question != null && !hasShownToday.value && hasChecked.value) {
        val selectedOption = remember { mutableStateOf<String?>(null) }
        val hasSubmitted = remember { mutableStateOf(false) }
        val isCorrect = remember { mutableStateOf<Boolean?>(null) }


        LaunchedEffect(hasSubmitted.value) {
            if (hasSubmitted.value) {
                kotlinx.coroutines.delay(2000L)
                dailyQuestionViewModel.markAsShownToday()
                hasShownToday.value = true
            }
        }

        AlertDialog(
            onDismissRequest = {
                if (hasSubmitted.value) {
                    dailyQuestionViewModel.markAsShownToday()
                    hasShownToday.value = true
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.daily_question),
                    style = questionHeader
                )
            },
            text = {
                Column {
                    Text(
                        text = question!!.questionText,
                        style = questionTextStyle
                    )
                    QuestionOption("A", question!!.optionA, selectedOption.value) { selectedOption.value = it }
                    QuestionOption("B", question!!.optionB, selectedOption.value) { selectedOption.value = it }
                    question!!.optionC?.takeIf { it.isNotBlank() }?.let { optC ->
                        QuestionOption("C", optC, selectedOption.value) { selectedOption.value = it }
                    }
                    question!!.optionD?.takeIf { it.isNotBlank() }?.let { optD ->
                        QuestionOption("D", optD, selectedOption.value) { selectedOption.value = it }
                    }

                    if (hasSubmitted.value && isCorrect.value != null) {
                        Spacer(modifier = Modifier.height(16.dp))

                        val unknownText = stringResource(id = R.string.unknown)
                        val correctAnswer = when (question!!.correctOption) {
                            "A" -> question!!.optionA
                            "B" -> question!!.optionB
                            "C" -> question!!.optionC ?: unknownText
                            "D" -> question!!.optionD ?: unknownText
                            else -> unknownText
                        }

                        val feedbackMessage = if (isCorrect.value == true) {
                            stringResource(R.string.correct_answer)
                        } else {
                            stringResource(R.string.wrong_answer, correctAnswer)
                        }

                        Text(
                            text = feedbackMessage,
                            color = if (isCorrect.value == true) Color.Green else Color.Red,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        selectedOption.value?.let {
                            isCorrect.value = it == question!!.correctOption
                            hasSubmitted.value = true
                            dailyQuestionViewModel.submitAnswer(it)
                        }
                    },
                    enabled = selectedOption.value != null && !hasSubmitted.value,
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Beige,
                        contentColor = Brown,
                    )
                ) {
                    Text(stringResource(R.string.send))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        dailyQuestionViewModel.markAsShownToday()
                        hasShownToday.value = true
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Brown,
                        contentColor = Beige,
                    )
                ) {
                    Text(stringResource(R.string.close))
                }
            },
            shape = RoundedCornerShape(16.dp),
            containerColor = Beige,
            titleContentColor = Brown,
            textContentColor = LightBrown
        )
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