package com.example.zadumite_frontend.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.example.zadumite_frontend.R

val AlegreyaFontFamily = FontFamily(
    Font(R.font.alegreya)
)

val entranceButton: TextStyle
    @Composable
    get() {
        return  TextStyle(
            fontSize = 24.sp,
            lineHeight = 30.sp,
            fontFamily = AlegreyaFontFamily,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000),
            textAlign = TextAlign.Center
        )
    }

val appTitle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 64.sp,
                    lineHeight = 80.45.sp,
                    fontFamily = FontFamily(Font(R.font.alegreya)),
                    fontWeight = FontWeight(800),
                    color = Brown,
        )
    }
