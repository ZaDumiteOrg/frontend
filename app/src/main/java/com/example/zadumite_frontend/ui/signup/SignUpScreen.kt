package com.example.zadumite_frontend.ui.signup

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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.data.model.user.SignUpRequest
import com.example.zadumite_frontend.ui.theme.Beige
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.LightBrown
import com.example.zadumite_frontend.ui.theme.LightGray
import com.example.zadumite_frontend.ui.theme.Red
import com.example.zadumite_frontend.ui.theme.White
import com.example.zadumite_frontend.ui.theme.enterText
import com.example.zadumite_frontend.ui.theme.entranceButton
import com.example.zadumite_frontend.ui.theme.userCredentials
import com.example.zadumite_frontend.utils.validation.isValidEmail
import com.example.zadumite_frontend.utils.validation.isValidPassword
import org.koin.androidx.compose.koinViewModel


@Composable
fun SignUpScreen(
    onNavigateBack: () -> Unit,
    onNavigateToWordScreen: () -> Unit,
    viewModel: SignUpViewModel = koinViewModel(),
) {

    var firstName by remember {
        mutableStateOf("")
    }

    var lastName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val signUpState by viewModel.signUpState.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)

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
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                        text = stringResource(R.string.first_name),
                        style = userCredentials,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 16.dp)
                    )
                }
                OutlinedTextField(
                    value = firstName,
                    onValueChange = {firstName = it},
                    label = {
                        Text(
                            text = stringResource(R.string.enter_first_name),
                            style = enterText,
                            modifier = Modifier.alpha(0.41f)
                        )
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
                        text = stringResource(R.string.last_name),
                        style = userCredentials,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 16.dp)
                    )
                }
                OutlinedTextField(
                    value = lastName,
                    onValueChange = {lastName = it},
                    label = {
                        Text(
                            text = stringResource(R.string.enter_last_name),
                            style = enterText,
                            modifier = Modifier.alpha(0.41f)
                        )
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
                            modifier = Modifier
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

                signUpState?.let { result ->
                    when {
                        result.isSuccess -> {
                            LaunchedEffect(Unit) {
                                onNavigateToWordScreen()
                            }
                        }
                        result.isFailure -> {
                            Text(
                                text = "Sign-up failed: ${result.exceptionOrNull()?.message}",
                                color = Red,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedButton(
                    onClick = {
                        when {
                            !isValidEmail(email) -> {
                                errorMessage = context.getString(R.string.invalid_email)
                            }
                            !isValidPassword(password) -> {
                                errorMessage = context.getString(R.string.invalid_password)
                            }
                            else -> {
                                errorMessage = ""
                                val user = SignUpRequest(firstName, lastName, email, password)
                                viewModel.signUp(user) {
                                        onNavigateToWordScreen()

                                }
                            }
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

                if (isLoading) {
                    CircularProgressIndicator(
                        color = LightBrown,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

