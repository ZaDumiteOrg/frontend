package com.example.zadumite_frontend
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.ui.theme.Beige
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.White
import com.example.zadumite_frontend.ui.theme.appTitle
import com.example.zadumite_frontend.ui.theme.entranceButton

@Composable
fun StartScreen (
    onNavigateToSignUp: () -> Unit,
    onNavigateToLogIn: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = stringResource(R.string.app_title),
                style = appTitle,
                modifier = Modifier.padding(bottom = 32.dp)
            )
            OutlinedButton(
                onClick = onNavigateToLogIn,
                border = BorderStroke(1.dp, Brown),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Beige,contentColor = Brown),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .width(277.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = stringResource(R.string.login_button),
                    style = entranceButton,
                )
            }
            OutlinedButton(
                onClick = onNavigateToSignUp,
                border = BorderStroke(1.dp, Brown),
                shape = RoundedCornerShape(size = 39.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Brown),
                modifier = Modifier
                    .width(277.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = stringResource(R.string.signup_button),
                    style = entranceButton,
                    modifier = Modifier
                        .width(131.dp)
                        .height(30.dp)
                )
            }
        }
    }
}