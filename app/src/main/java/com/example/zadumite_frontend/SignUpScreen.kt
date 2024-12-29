package com.example.zadumite_frontend

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.ui.theme.Beige
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.LightBrown
import com.example.zadumite_frontend.ui.theme.LightGray
import com.example.zadumite_frontend.ui.theme.White
import com.example.zadumite_frontend.ui.theme.enterText
import com.example.zadumite_frontend.ui.theme.entranceButton
import com.example.zadumite_frontend.ui.theme.userCredentials

@Composable
fun SignUpScreen(
    onNavigateBack: () -> Unit,
    onNavigateToMain: () -> Unit
) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isValid by remember {
        mutableStateOf(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = Brown
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.email),
                        style = userCredentials,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 16.dp)
                    )
                }
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = {
                        Text(
                            text = stringResource(R.string.example_email),
                            style = enterText,
                            modifier = Modifier
                                .alpha(0.41f)
                                .width(139.dp)
                                .height(20.dp))
                            },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Brown,
                        unfocusedBorderColor = LightBrown,
                        backgroundColor = LightGray,
                        cursorColor = LightBrown,
                        textColor = LightBrown
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    Text(
                        text = stringResource(R.string.password),
                        style = userCredentials,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 16.dp)
                    )
                }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = {
                        Text(
                            text = stringResource(R.string.example_password),
                            style = enterText,
                            modifier =  Modifier
                                .alpha(0.41f)
                                .width(139.dp)
                                .height(20.dp))
                            },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(55.dp),
                    shape = RoundedCornerShape(50.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Brown,
                        unfocusedBorderColor = LightBrown,
                        backgroundColor = LightGray,
                        cursorColor = LightBrown,
                        textColor = LightBrown
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = {
                        isValid = isValidCredentials(email, password)
                        if (isValid) {
                            onNavigateToMain
                        }
                    } ,
                    border = BorderStroke(1.dp, Brown),
                    shape = RoundedCornerShape(size = 39.dp),
                    colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Beige, contentColor = Brown),
                    modifier = Modifier
                        .width(277.dp)
                        .height(48.dp)
                ) {
                    Text(
                        text = stringResource(R.string.signup),
                        style = entranceButton
                    )
                }
            }
        }
    }
}

private fun isValidCredentials(email: String, password: String): Boolean {
    val emailPattern = Regex("[a-zA-Z0–9._-]+@[a-z]+\\.+[a-z]+")
    val passwordPattern = Regex("^(?=.*[0–9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$")
    return emailPattern.matches(email) && passwordPattern.matches(password)
}