package com.example.zadumite_frontend.ui.word

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.navigation.Screen
import com.example.zadumite_frontend.session.SessionViewModel
import com.example.zadumite_frontend.ui.scaffold.ZaDumiteScaffold
import com.example.zadumite_frontend.ui.theme.errorMessageStyle
import org.koin.androidx.compose.koinViewModel

@Composable
fun WordOfTheWeekScreen(
    navController: NavController,
    viewModel: WordViewModel = koinViewModel(),
    sessionViewModel: SessionViewModel = koinViewModel()
) {
    ZaDumiteScaffold(
        currentRoute = Screen.Word.route,
        onNavigateToWords = {},
        onNavigateToHome = {}
    ) {
        val word = viewModel.wordOfTheWeek.value
        val loading = viewModel.loading.value

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            contentAlignment = Alignment.Center
        ) {
            when {
                loading -> {
                    CircularProgressIndicator()
                }
                word != null -> {
                    WordCard(word = word)
                }
                else -> {
                    Text(
                        text = stringResource(R.string.fetching_word_failed),
                        style = errorMessageStyle)
                }
            }
        }
    }
}