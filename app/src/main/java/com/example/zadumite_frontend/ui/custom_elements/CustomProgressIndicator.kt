package com.example.zadumite_frontend.ui.custom_elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.ui.theme.LightBrown

@Composable
fun CustomProgressIndicator(
    modifier: Modifier = Modifier,
    color: Color = LightBrown,
    strokeWidth: Dp = 4.dp,
    size: Dp = 50.dp
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(size)
    ) {
        CircularProgressIndicator(
            color = color,
            strokeWidth = strokeWidth,
            modifier = Modifier.size(size)
        )
    }
}
