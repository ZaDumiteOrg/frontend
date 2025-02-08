package com.example.zadumite_frontend.ui.user_words

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.ui.theme.Gray
import com.example.zadumite_frontend.ui.theme.errorMessageStyle
import com.example.zadumite_frontend.ui.theme.usersWord
import com.example.zadumite_frontend.ui.theme.usersWordExample
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.zadumite_frontend.network.monitor.ConnectivityObserver
import com.example.zadumite_frontend.network.monitor.NetworkViewModel
import com.example.zadumite_frontend.ui.custom_elements.CustomProgressIndicator
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.LightGray

@Composable
fun UserWordsScreen(
    viewModel: UserWordsViewModel = koinViewModel(),
    networkViewModel: NetworkViewModel = koinViewModel()
) {
    val context = LocalContext.current
    val wordsState by viewModel.userWordsState
    val loading by viewModel.isLoading
    val networkStatus by networkViewModel.networkStatus.collectAsState()
    var previousNetworkStatus by remember { mutableStateOf(networkStatus) }

    LaunchedEffect(networkStatus) {
        if (networkStatus != previousNetworkStatus) {
            if (networkStatus == ConnectivityObserver.Status.Available) {
                viewModel.fetchUserWords()
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.no_internet_connection),
                    Toast.LENGTH_SHORT
                ).show()
            }
            previousNetworkStatus = networkStatus
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        when {
            loading -> {
                CustomProgressIndicator(Modifier.align(Alignment.Center))
            }

            wordsState == null -> {
                Text(
                    text = stringResource(R.string.no_words_error),
                    style = errorMessageStyle,
                    textAlign = TextAlign.Center
                )
            }

            wordsState!!.isSuccess ->  {
                val words =  wordsState!!.getOrNull() ?: emptyList()
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp)
                ) {
                    items(words) { word ->
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardColors(containerColor = LightGray,
                                disabledContainerColor = Gray,
                                disabledContentColor = Brown,
                                contentColor = Brown),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth()
                            ) {
                                Text(
                                    text = word.word,
                                    style = usersWord
                                )
                                Text(
                                    text = word.description,
                                    style = usersWordExample,
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }
                        }
                    }
                }
            }

            else -> {
                Text(
                    text = stringResource(R.string.no_words_error),
                    style = errorMessageStyle,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
