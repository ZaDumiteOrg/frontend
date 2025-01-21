package com.example.zadumite_frontend.ui.user_words

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.ui.theme.Gray
import com.example.zadumite_frontend.ui.theme.LightBrown
import com.example.zadumite_frontend.ui.theme.errorMessageStyle
import com.example.zadumite_frontend.ui.theme.usersWord
import com.example.zadumite_frontend.ui.theme.usersWordExample
import org.koin.androidx.compose.koinViewModel

@Composable
fun UserWordsScreen(viewModel: UserWordsViewModel = koinViewModel(), userId: Int) {
    val words = viewModel.userWords.value
    val loading = viewModel.loading.value

    LaunchedEffect(Unit) {
        viewModel.fetchUserWords(userId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
    ) {
        when {
            loading -> {
                CircularProgressIndicator(
                    color = LightBrown,
                    modifier = Modifier.align(Alignment.Center))
            }
            words.isNotEmpty() -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 100.dp)
                ) {
                    items(words) { word ->
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            backgroundColor = Gray,
                            elevation = 4.dp,
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
