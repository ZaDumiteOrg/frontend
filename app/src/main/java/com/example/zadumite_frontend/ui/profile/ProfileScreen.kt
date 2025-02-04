package com.example.zadumite_frontend.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
    val profileState by viewModel.profileState.observeAsState()
    val isLoading by viewModel.isLoading.observeAsState(initial = false)
    val errorMessage by viewModel.errorMessage.observeAsState()
    val networkStatus by networkViewModel.networkStatus.collectAsState()
    val isNetworkAvailable = networkStatus == ConnectivityObserver.Status.Available

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
                            viewModel.logout {
                                onNavigateToStartPage()
                            }
                        }
                    )
                }
            }
        }
    }
}