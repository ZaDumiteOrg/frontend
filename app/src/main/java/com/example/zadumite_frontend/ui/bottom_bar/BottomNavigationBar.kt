package com.example.zadumite_frontend.ui.bottom_bar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.navigation.Screen
import com.example.zadumite_frontend.ui.theme.Beige
import com.example.zadumite_frontend.ui.theme.Brown

@Composable
fun BottomNavigationBar(
    onNavigateToWords: () -> Unit,
    onNavigateToHome: () -> Unit,
    onNavigateToProfile: () -> Unit,
    currentRoute: String?
) {
    BottomNavigation(
        backgroundColor = Beige,
        contentColor = Brown
    ) {
        BottomNavigationItem(
            selected = currentRoute == Screen.UserWords.route,
            onClick = {
                onNavigateToWords()
            },
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = stringResource(R.string.word)) },
            label = { Text(text = stringResource(R.string.words)) }
        )
        BottomNavigationItem(
            selected = currentRoute == Screen.Word.route,
            onClick = {
                onNavigateToHome()
            },
            icon = { Icon(Icons.Default.Home, contentDescription = stringResource(R.string.home)) },
        )
        BottomNavigationItem(
            selected = currentRoute == Screen.Profile.route,
            onClick = {
                onNavigateToProfile()
            },
            icon = { Icon(Icons.Default.Person, contentDescription = stringResource(R.string.profile)) },
            label = { Text(text = stringResource(R.string.profile)) }
        )
    }
}
