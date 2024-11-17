package com.example.zadumite_frontend
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material3.Text
import androidx.navigation.NavController

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.zadumite_frontend.ui.theme.entranceButton

@Composable
fun StartScreen (navController: NavController){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "За думите",
                style = TextStyle(
                    fontSize = 64.sp,
                    lineHeight = 80.45.sp,
                    fontFamily = FontFamily(Font(R.font.alegreya)),
                    fontWeight = FontWeight(800),
                    color = Color(0xFF4D2D18),
                ),
                modifier = Modifier.padding(bottom = 32.dp)
            )
            OutlinedButton(
                onClick = { navController.navigate(route = Screen.LogIn.route) },
                border = BorderStroke(1.dp, Color(0xFF4D2D18)),
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(backgroundColor = Color(0xFFCBBA9E),contentColor = Color(0xFF4D2D18)),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .width(277.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = "Влизане",
                    style = entranceButton,
                )
            }
            OutlinedButton(
                onClick = { navController.navigate(route = Screen.SignUp.route) },
                border = BorderStroke(1.dp, Color(0xFF4D2D18)),
                shape = RoundedCornerShape(size = 39.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF4D2D18)),
                modifier = Modifier
                    .width(277.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = "Регистрация",
                    style = entranceButton,
                    modifier = Modifier
                        .width(131.dp)
                        .height(30.dp)
                )
            }
        }
    }
}