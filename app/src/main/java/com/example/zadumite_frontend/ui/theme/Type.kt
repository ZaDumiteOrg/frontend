package com.example.zadumite_frontend.ui.theme

import androidx.compose.runtime.Composable
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
            color = Black,
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

val userCredentials: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 15.sp,
            lineHeight = 18.86.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(500),
            color = Brown,
        )
    }

val enterText: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(500),
            color = Brown,
        )
    }

val errorMessageStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            color = Red,
        )
    }

val scaffoldTitle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 28.sp,
            lineHeight = 35.2.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(800),
            color = Brown,
            )
    }

val wordOfTheWeekHeader: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 24.sp,
            lineHeight = 30.17.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(800),
            color = LightBrown,
            )
    }

val wordOfTheWeek: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 40.sp,
            lineHeight = 50.28.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(800),
            color = Brown,
            )
    }

val wordDescription: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 20.sp,
            lineHeight = 25.14.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(400),
            color = LightBrown
        )
    }

val wordExample: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 20.sp,
            lineHeight = 25.14.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(500),
            color = Brown
        )
    }

val usersWord: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 16.sp,
            lineHeight = 20.11.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(500),
            color = Brown
        )
    }

val usersWordExample: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 14.sp,
            lineHeight = 17.6.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(400),
            color = Brown
        )
    }

val myProfile: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 24.sp,
            lineHeight = 30.17.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(500),
            color = Brown,
        )
    }

val questionHeader: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 24.sp,
            lineHeight = 30.17.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(400),
            color = Brown
        )
    }

val questionTextStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 20.sp,
            lineHeight = 30.17.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(400),
            color = LightBrown
        )
    }

val answerTextStyle: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontSize = 20.sp,
            lineHeight = 30.17.sp,
            fontFamily = FontFamily(Font(R.font.alegreya)),
            fontWeight = FontWeight(400),
            color = LightBrown
        )
    }

val leaderboardHeader: TextStyle
@Composable
get() {
    return TextStyle(
        fontSize = 40.sp,
        lineHeight = 50.28.sp,
        fontFamily = FontFamily(Font(R.font.alegreya)),
        fontWeight = FontWeight(800),
        color = Brown
    )
}