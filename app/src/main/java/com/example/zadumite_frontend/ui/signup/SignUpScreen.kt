package com.example.zadumite_frontend.ui.signup

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.MainActivity
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.data.model.user.SignUpRequest
import com.example.zadumite_frontend.network.monitor.ConnectivityObserver
import com.example.zadumite_frontend.network.monitor.NetworkViewModel
import com.example.zadumite_frontend.ui.custom_elements.CustomButton
import com.example.zadumite_frontend.ui.custom_elements.CustomOutlinedTextField
import com.example.zadumite_frontend.ui.custom_elements.CustomProgressIndicator
import com.example.zadumite_frontend.ui.custom_elements.PasswordTextField
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.Red
import com.example.zadumite_frontend.ui.theme.White
import com.example.zadumite_frontend.ui.theme.errorMessageStyle
import com.example.zadumite_frontend.ui.utils.validation.isValidEmail
import com.example.zadumite_frontend.ui.utils.validation.isValidPassword
import org.koin.androidx.compose.koinViewModel


@Composable
fun SignUpScreen(
    onNavigateBack: () -> Unit,
    onNavigateToWordScreen: () -> Unit,
    viewModel: SignUpViewModel = koinViewModel(),
    networkViewModel: NetworkViewModel = koinViewModel()
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
    val signUpState by viewModel.signUpState
    val isLoading by viewModel.isLoading
    val networkStatus by networkViewModel.networkStatus.collectAsState()
    val isNetworkAvailable = networkStatus == ConnectivityObserver.Status.Available

    LaunchedEffect(signUpState) {
        signUpState?.let { result ->
            result.fold(
                onSuccess = {
                    onNavigateToWordScreen()
                },
                onFailure = { exception ->
                    errorMessage = exception.message ?: context.getString(R.string.signup_failed)
                }
            )
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
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                androidx.compose.material3.IconButton(
                    onClick = onNavigateBack,
                ) {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back),
                        tint = Brown
                    )
                }
            }

            if (!isNetworkAvailable) {
                Text(
                    text = stringResource(R.string.no_internet_connection),
                    color = Red,
                    style = errorMessageStyle,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
            }

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                CustomOutlinedTextField(
                    value = firstName,
                    onValueChange = {firstName = it},
                    label = stringResource(R.string.first_name),
                    placeholder = stringResource(R.string.enter_first_name),
                    isReadOnly = !isNetworkAvailable
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomOutlinedTextField(
                    value = lastName,
                    onValueChange = {lastName = it},
                    label = stringResource(R.string.last_name),
                    placeholder = stringResource(R.string.enter_last_name),
                    isReadOnly = !isNetworkAvailable
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomOutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = stringResource(R.string.email),
                    placeholder = stringResource(R.string.example_email),
                    isReadOnly = !isNetworkAvailable
                )

                Spacer(modifier = Modifier.height(16.dp))

                PasswordTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = stringResource(R.string.password),
                    placeholder = stringResource(R.string.example_password),
                    isReadOnly = !isNetworkAvailable,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomButton(
                    text = stringResource(R.string.signup),
                    isEnabled = isNetworkAvailable && !isLoading,
                    onClick = {
                        if(!isLoading) {
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
                                     viewModel.signUp(user, context) {
                                         (context as? MainActivity)?.requestNotificationPermission()
                                         onNavigateToWordScreen()
                                     }
                                 }
                            }
                        }
                    }
                )
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        style = errorMessageStyle
                    )
                }
                if (isLoading) {
                    CustomProgressIndicator(
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

