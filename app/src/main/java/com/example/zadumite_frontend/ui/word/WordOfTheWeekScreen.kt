package com.example.zadumite_frontend.ui.word

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.network.monitor.ConnectivityObserver
import com.example.zadumite_frontend.network.monitor.NetworkViewModel
import com.example.zadumite_frontend.ui.custom_elements.CustomProgressIndicator
import com.example.zadumite_frontend.ui.theme.errorMessageStyle
import org.koin.androidx.compose.koinViewModel

@Composable
fun WordOfTheWeekScreen(
    viewModel: WordViewModel = koinViewModel(),
    networkViewModel: NetworkViewModel = koinViewModel()
) {

    val word = viewModel.wordOfTheWeek.value
    val loading = viewModel.loading.value
    val networkStatus by networkViewModel.networkStatus.collectAsState()
    val isNetworkAvailable = networkStatus == ConnectivityObserver.Status.Available


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
            loading -> {
                CustomProgressIndicator(modifier = Modifier.padding(top = 16.dp))
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