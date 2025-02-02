package com.example.zadumite_frontend.ui.custom_elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.ui.theme.Beige
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.entranceButton

@Composable
fun CustomButton(
    text: String,
    isEnabled: Boolean = true,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        border = BorderStroke(1.dp, Brown),
        shape = RoundedCornerShape(39.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Beige,
            contentColor = Brown,
        ),
        modifier = Modifier
            .width(277.dp)
            .height(48.dp),
        enabled = isEnabled
    ) {
        Text(text = text, style = entranceButton)
    }
}
