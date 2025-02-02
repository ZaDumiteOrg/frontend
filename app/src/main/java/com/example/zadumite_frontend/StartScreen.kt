package com.example.zadumite_frontend
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.ui.custom_elements.CustomButton
import com.example.zadumite_frontend.ui.theme.White
import com.example.zadumite_frontend.ui.theme.appTitle

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
            CustomButton(
                text = stringResource(R.string.login),
                isEnabled = true,
                onClick = onNavigateToLogIn
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomButton(
                text = stringResource(R.string.signup),
                isEnabled = true,
                onClick = onNavigateToSignUp
            )
        }
    }
}