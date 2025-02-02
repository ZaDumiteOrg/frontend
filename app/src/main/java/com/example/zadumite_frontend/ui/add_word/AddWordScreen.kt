package com.example.zadumite_frontend.ui.add_word

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.ui.theme.LightBrown
import com.example.zadumite_frontend.ui.theme.White
import com.example.zadumite_frontend.ui.theme.errorMessageStyle
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.text.style.TextAlign
import com.example.zadumite_frontend.network.monitor.ConnectivityObserver
import com.example.zadumite_frontend.network.monitor.NetworkViewModel
import com.example.zadumite_frontend.ui.custom_elements.CustomButton
import com.example.zadumite_frontend.ui.custom_elements.CustomOutlinedTextField
import com.example.zadumite_frontend.ui.theme.Red

@Composable
fun AddWordScreen(
    viewModel: AddWordViewModel = koinViewModel(),
    networkViewModel: NetworkViewModel = koinViewModel()
) {
    var word by remember { mutableStateOf("") }
    var example by remember { mutableStateOf("") }
    var definition by remember { mutableStateOf("") }
    var synonym by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    val addWordResult by viewModel.addWordResult.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val context = LocalContext.current
    val networkStatus by networkViewModel.networkStatus.collectAsState()
    val isNetworkAvailable = networkStatus == ConnectivityObserver.Status.Available


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (!isNetworkAvailable) {
                Text(
                    text = stringResource(R.string.no_internet_connection),
                    color = Red,
                    style = errorMessageStyle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            CustomOutlinedTextField(
                value = word,
                onValueChange = { input ->
                    if (input.all { it.isLetter() || it.isWhitespace() }) {
                        word = input
                    }
                },
                label = stringResource(R.string.word),
                isReadOnly = !isNetworkAvailable
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomOutlinedTextField(
                value = definition,
                onValueChange = { input ->
                    if (input.all { it.isLetter() || it.isWhitespace() }) {
                        definition = input
                    } },
                label = stringResource(R.string.word_description),
                isReadOnly = !isNetworkAvailable
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomOutlinedTextField(
                value = example,
                onValueChange = {input ->
                    if (input.all { it.isLetter() || it.isWhitespace() }) {
                        example = input
                    }},
                label = stringResource(R.string.word_example),
                isReadOnly = !isNetworkAvailable
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomOutlinedTextField(
                value = synonym,
                onValueChange = { input ->
                    if (input.all { it.isLetter() || it.isWhitespace() }) {
                        synonym = input
                    } },
                label = stringResource(R.string.synonym),
                isReadOnly = !isNetworkAvailable
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                text = stringResource(R.string.add_word),
                isEnabled = isNetworkAvailable && !isLoading,
                onClick = {
                    errorMessage = if (word.isBlank() || example.isBlank() || definition.isBlank()) {
                        context.getString(R.string.add_word_error)
                    } else {
                        viewModel.addWord(word, definition, example, synonym.takeIf { it.isNotBlank() })
                        ""
                    }
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    style = errorMessageStyle,
                    color = Color.Red
                )
            }

            addWordResult?.let { result ->
                result.onSuccess {
                    Toast.makeText(context, stringResource(R.string.successfully_added_word), Toast.LENGTH_SHORT).show()
                    word = ""
                    definition = ""
                    example = ""
                    synonym = ""
                }.onFailure { error ->
                    errorMessage = error.localizedMessage ?: stringResource(R.string.error_adding_wrord)
                }
            }

            if (isLoading) {
                CircularProgressIndicator(
                    color = LightBrown
                )
            }
        }
    }
}
