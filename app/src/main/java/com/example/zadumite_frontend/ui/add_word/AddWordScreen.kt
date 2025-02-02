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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.ui.theme.Beige
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.LightBrown
import com.example.zadumite_frontend.ui.theme.White
import com.example.zadumite_frontend.ui.theme.entranceButton
import com.example.zadumite_frontend.ui.theme.errorMessageStyle
import com.example.zadumite_frontend.ui.theme.userCredentials
import org.koin.androidx.compose.koinViewModel
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import com.example.zadumite_frontend.network.monitor.ConnectivityObserver
import com.example.zadumite_frontend.network.monitor.NetworkViewModel

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

    LaunchedEffect(networkStatus) {
        if (networkStatus == ConnectivityObserver.Status.Lost ||
            networkStatus == ConnectivityObserver.Status.Unavailable
        ) {
            Toast.makeText(context, context.getString(R.string.no_internet_connection), Toast.LENGTH_SHORT).show()
        }
    }

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
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = stringResource(R.string.word),
                style = userCredentials
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = word,
                onValueChange = { input ->
                    if (input.all { it.isLetter() || it.isWhitespace() }) {
                        word = input
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                modifier = Modifier.fillMaxWidth(0.9f),
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Brown,
                    unfocusedBorderColor = LightBrown,
                    cursorColor = LightBrown,
                    focusedTextColor = LightBrown
                ),
                readOnly = !isNetworkAvailable
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.word_description),
                style = userCredentials
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = definition,
                onValueChange = { definition = it },
                modifier = Modifier.fillMaxWidth(0.9f),
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Brown,
                    unfocusedBorderColor = LightBrown,
                    cursorColor = LightBrown,
                    focusedTextColor = LightBrown
                ),
                readOnly = !isNetworkAvailable
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.word_example),
                style = userCredentials
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = example,
                onValueChange = { example = it },
                modifier = Modifier.fillMaxWidth(0.9f),
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Brown,
                    unfocusedBorderColor = LightBrown,
                    cursorColor = LightBrown,
                    focusedTextColor = LightBrown
                ),
                readOnly = !isNetworkAvailable
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.synonym),
                style = userCredentials
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = synonym,
                onValueChange = { synonym = it },
                modifier = Modifier.fillMaxWidth(0.9f),
                shape = RoundedCornerShape(50),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Brown,
                    unfocusedBorderColor = LightBrown,
                    cursorColor = LightBrown,
                    focusedTextColor = LightBrown
                ),
                readOnly = !isNetworkAvailable
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    errorMessage = if (word.isBlank() || example.isBlank() || definition.isBlank()) {
                        context.getString(R.string.add_word_error)
                    } else {
                        viewModel.addWord(word, definition, example, synonym.takeIf { it.isNotBlank() })
                        ""
                    }
                },
                modifier = Modifier.width(277.dp),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Beige,
                    contentColor = Brown
                ),
                enabled = isNetworkAvailable && !isLoading
            ) {
                Text(
                    text = stringResource(R.string.add_word),
                    style = entranceButton
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    style = errorMessageStyle,
                    color = Color.Red
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

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

            Spacer(modifier = Modifier.height(8.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    color = LightBrown
                )
            }
        }
    }
}
