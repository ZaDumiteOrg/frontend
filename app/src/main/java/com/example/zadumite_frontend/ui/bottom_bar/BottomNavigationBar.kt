package com.example.zadumite_frontend.ui.bottom_bar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.zadumite_frontend.R
import com.example.zadumite_frontend.ui.theme.Beige
import com.example.zadumite_frontend.ui.theme.Brown

@Composable
fun BottomNavigationBar() {
    BottomNavigation(
        backgroundColor = Beige,
        contentColor = Brown
    ) {
        BottomNavigationItem(
            selected = false,
            onClick = { println("Clicked word list") },
            icon = { Icon(Icons.Default.List, contentDescription = "Words") },
            label = { Text(text = stringResource(R.string.words)) }
        )
        BottomNavigationItem(
            selected = true,
            onClick = { println("Clicked Home") },
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
        )
        BottomNavigationItem(
            selected = false,
            onClick = { println("CLicked profile") },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text(text = stringResource(R.string.profile)) }
        )
    }
}
