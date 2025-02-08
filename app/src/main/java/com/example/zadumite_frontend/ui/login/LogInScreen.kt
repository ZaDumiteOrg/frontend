package com.example.zadumite_frontend.ui.login

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.MainActivity
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.network.monitor.ConnectivityObserver
import com.example.zadumite_frontend.network.monitor.NetworkViewModel
import com.example.zadumite_frontend.ui.custom_elements.CustomButton
import com.example.zadumite_frontend.ui.custom_elements.CustomOutlinedTextField
import com.example.zadumite_frontend.ui.custom_elements.CustomProgressIndicator
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.Red
import com.example.zadumite_frontend.ui.theme.White
import com.example.zadumite_frontend.ui.theme.errorMessageStyle
import com.example.zadumite_frontend.ui.utils.validation.isValidEmail
import com.example.zadumite_frontend.ui.utils.validation.isValidPassword
import org.koin.androidx.compose.koinViewModel
import com.example.zadumite_frontend.ui.custom_elements.PasswordTextField


@Composable
fun LogInScreen(
    onNavigateBack: ()-> Unit,
    onNavigateToWordScreen: ()-> Unit,
    onNavigateToAddWordScreen: ()-> Unit,
    logInViewModel: LogInViewModel = koinViewModel(),
    networkViewModel: NetworkViewModel = koinViewModel()
) {
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
    val loginState by logInViewModel.loginState
    val isLoading by logInViewModel.isLoading
    val networkStatus by networkViewModel.networkStatus.collectAsState()
    val isNetworkAvailable = networkStatus == ConnectivityObserver.Status.Available

    LaunchedEffect(loginState) {
        loginState?.let { result ->
            result.fold(
                onSuccess = {
                    onNavigateToWordScreen()
                },
                onFailure = { exception ->
                    errorMessage = exception.message ?: context.getString(R.string.login_failed)
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
                IconButton(
                    onClick = onNavigateBack,
                ) {
                    Icon(
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
                    isReadOnly = !isNetworkAvailable
                )

                Spacer(modifier = Modifier.height(16.dp))

                CustomButton(
                    text = stringResource(R.string.login),
                    isEnabled = isNetworkAvailable && !isLoading,
                    onClick = {
                        if(!isLoading) {
                            when {
                                !isValidEmail(email) -> {
                                    errorMessage = context.getString(R.string.wrong_email)
                                }

                                !isValidPassword(password) -> {
                                    errorMessage = context.getString(R.string.wrong_password)
                                }

                                else -> {
                                    errorMessage = ""
                                    logInViewModel.logIn(email, password, context) { role ->
                                        if (role == null) {
                                            errorMessage = context.getString(R.string.login_failed)
                                            return@logIn
                                        }


                                        when (role) {
                                            "admin" -> {
                                                onNavigateToAddWordScreen()
                                            }

                                            else -> {
                                                onNavigateToWordScreen()
                                                (context as? MainActivity)?.requestNotificationPermission()
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } ,
                )
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        style = errorMessageStyle
                    )
                }
                if (isLoading) {
                    CustomProgressIndicator()
                }
            }
        }
    }
}

