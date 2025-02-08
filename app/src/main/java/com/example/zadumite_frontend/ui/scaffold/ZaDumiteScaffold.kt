package com.example.zadumite_frontend.ui.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.navigation.Screen
import com.example.zadumite_frontend.ui.bottom_bar.BottomNavigationBar
import com.example.zadumite_frontend.ui.theme.Beige
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.scaffoldTitle

@Composable
fun ZaDumiteScaffold(
    currentRoute: String?,
    onNavigateToWords: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {

    val context = LocalContext.current
    val title = context.getString(R.string.app_title)


    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Beige,
                contentColor = Brown,
                elevation = 4.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = title,
                        style = scaffoldTitle,
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        bottomBar = {
            if (currentRoute in listOf(Screen.Word.route, Screen.UserWords.route, Screen.Profile.route)) {
                BottomNavigationBar(
                    currentRoute = currentRoute,
                    onNavigateToWords = onNavigateToWords,
                    onNavigateToHome = onNavigateToHome,
                    onNavigateToProfile = onNavigateToProfile
                )
            }
        }
    ) { innerPadding ->
        content(innerPadding)
    }
}

