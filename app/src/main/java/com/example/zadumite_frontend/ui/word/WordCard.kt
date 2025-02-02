package com.example.zadumite_frontend.ui.word

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.data.model.word.Word
import com.example.zadumite_frontend.ui.theme.Beige
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.ui.theme.wordDescription
import com.example.zadumite_frontend.ui.theme.wordExample
import com.example.zadumite_frontend.ui.theme.wordOfTheWeek
import com.example.zadumite_frontend.ui.theme.wordOfTheWeekHeader


@Composable
fun WordCard(word:Word) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Beige
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.word_of_week),
                style = wordOfTheWeekHeader,
                textAlign = TextAlign.Center
            )
            Text(
                text = word.word,
                style = wordOfTheWeek,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(R.string.word_description),
                style = wordDescription,
                textAlign = TextAlign.Center

            )
            Text(
                text = word.description,
                style = wordDescription,
                textAlign = TextAlign.Center

            )
            Text(
                text = stringResource(R.string.word_example),
                style = wordExample,
                textAlign = TextAlign.Center
            )
            Text(
                text = word.example,
                style = wordExample,
                textAlign = TextAlign.Center
            )
        }
    }
}