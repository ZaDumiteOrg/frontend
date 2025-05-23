package com.example.zadumite_frontend.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
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
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.network.monitor.ConnectivityObserver
import com.example.zadumite_frontend.network.monitor.NetworkViewModel
import com.example.zadumite_frontend.ui.custom_elements.CustomButton
import com.example.zadumite_frontend.ui.custom_elements.CustomOutlinedTextField
import com.example.zadumite_frontend.ui.custom_elements.CustomProgressIndicator
import com.example.zadumite_frontend.ui.theme.Beige
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.LightBrown
import com.example.zadumite_frontend.ui.theme.Red
import com.example.zadumite_frontend.ui.theme.errorMessageStyle
import com.example.zadumite_frontend.ui.theme.myProfile
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProfileScreen(
    onNavigateToStartPage: () -> Unit,
    viewModel: ProfileViewModel = koinViewModel(),
    networkViewModel: NetworkViewModel= koinViewModel()
) {
    val context = LocalContext.current
    val profileState by viewModel.profileState
    val isLoading by viewModel.isLoading
    val errorMessage by viewModel.errorMessage
    val networkStatus by networkViewModel.networkStatus.collectAsState()
    val isNetworkAvailable = networkStatus == ConnectivityObserver.Status.Available
    var showDialog by remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        viewModel.fetchUserDetails(context)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        when {
            isLoading -> {
                CustomProgressIndicator()
            }

            errorMessage != null -> {
                Text(
                    text = errorMessage ?: context.getString(R.string.error_occurred),
                    style = errorMessageStyle
                )
            }

            profileState != null -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
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

                    Text(
                        text = stringResource(R.string.my_profile),
                        style = myProfile
                    )
                    CustomOutlinedTextField(
                        value = profileState?.firstName ?: "",
                        onValueChange = {},
                        label = stringResource(R.string.first_name),
                        isReadOnly = true
                    )

                    CustomOutlinedTextField(
                        value = profileState?.lastName ?: "",
                        onValueChange = {},
                        label = stringResource(R.string.last_name),
                        isReadOnly = true
                    )

                    CustomOutlinedTextField(
                        value = profileState?.email ?: "",
                        onValueChange = {},
                        label = stringResource(R.string.email),
                        isReadOnly = true
                    )

                    CustomOutlinedTextField(
                        value = stringResource(R.string.example_password),
                        onValueChange = {},
                        label = stringResource(R.string.password),
                        isReadOnly = true
                    )

                    CustomButton(
                        text = stringResource(R.string.logout),
                        isEnabled = isNetworkAvailable,
                        onClick = {
                            showDialog = true
                        }
                    )

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text(stringResource(R.string.logout_alert_title)) },
                            text = { Text(stringResource(R.string.logout_confirm)) },
                            confirmButton = {
                                CustomButton(
                                    text = stringResource(R.string.yes),
                                    isEnabled = isNetworkAvailable,
                                    onClick = {
                                        showDialog = false
                                        viewModel.logout {
                                            onNavigateToStartPage()
                                        }
                                    }
                                )
                            },
                            dismissButton = {
                                CustomButton(
                                    text = stringResource(R.string.no),
                                    isEnabled = isNetworkAvailable,
                                    onClick = { showDialog = false }
                                )
                            },
                            shape = RoundedCornerShape(16.dp),
                            containerColor = Beige,
                            titleContentColor = Brown,
                            textContentColor = LightBrown
                        )
                    }
                }
            }
        }
    }
}