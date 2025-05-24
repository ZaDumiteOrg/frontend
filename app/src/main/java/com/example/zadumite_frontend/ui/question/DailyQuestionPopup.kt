package com.example.zadumite_frontend.ui.question

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.data.model.question.Question
import com.example.zadumite_frontend.ui.theme.Beige
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.LightBrown
import com.example.zadumite_frontend.ui.theme.questionHeader
import com.example.zadumite_frontend.ui.theme.questionTextStyle


@Composable
fun DailyQuestionPopup(
    dailyQuestion: Question,
    onDismiss: () -> Unit,
    onAnswerSubmit: (selectedOption: String) -> Unit
) {
    var selectedOption by remember { mutableStateOf<String?>(null) }
    val hasSubmitted by remember { mutableStateOf(false) }
    val isCorrect by remember { mutableStateOf<Boolean?>(null) }


    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = stringResource(R.string.daily_question),
                style = questionHeader
            )
        },

        text = {
            Column {
                Text(
                    text = dailyQuestion.questionText,
                    style = questionTextStyle
                )
                QuestionOption("A", dailyQuestion.optionA, selectedOption) { selectedOption = it }
                QuestionOption("B", dailyQuestion.optionB, selectedOption) { selectedOption = it }
                dailyQuestion.optionC?.takeIf { it.isNotBlank() }?.let { optC ->
                    QuestionOption("C", optC, selectedOption) { selectedOption = it }
                }
                dailyQuestion.optionD?.takeIf { it.isNotBlank() }?.let { optD ->
                    QuestionOption("D", optD, selectedOption) { selectedOption = it }
                }

                if (hasSubmitted && isCorrect != null) {
                    Spacer(modifier = Modifier.height(16.dp))

                    val unknownText = stringResource(id = R.string.unknown)

                    val correctAnswer: String = when (dailyQuestion.correctOption) {
                        "A" -> dailyQuestion.optionA
                        "B" -> dailyQuestion.optionB
                        "C" -> dailyQuestion.optionC ?: unknownText
                        "D" -> dailyQuestion.optionD ?: unknownText
                        else -> unknownText
                    }

                    val feedbackMessage = if (isCorrect == true) {
                        stringResource(R.string.correct_answer)
                    } else {
                        stringResource(R.string.wrong_answer, correctAnswer)
                    }

                    Text(
                        text = feedbackMessage,
                        color = if (isCorrect == true) Color.Green else Color.Red,
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
            }
        },

        confirmButton = {
            Button(
                onClick = {
                    selectedOption?.let {
                        onAnswerSubmit(it)
                        onDismiss()
                    }
                },
                enabled = selectedOption != null,
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Beige,
                    contentColor = Brown,
                )
            ) {
                Text(stringResource(R.string.send))
            }
        },

        dismissButton = {
            TextButton(onClick = onDismiss, colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Brown,
                contentColor = Beige,
            )) {
                Text(stringResource(R.string.close))
            }
        },
        shape = RoundedCornerShape(16.dp),
        containerColor = Beige,
        titleContentColor = Brown,
        textContentColor = LightBrown
    )
}

