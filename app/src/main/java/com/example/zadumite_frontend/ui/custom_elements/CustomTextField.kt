package com.example.zadumite_frontend.ui.custom_elements

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.zadumite_frontend.ui.theme.Brown
import com.example.zadumite_frontend.ui.theme.DarkGray
import com.example.zadumite_frontend.ui.theme.LightBrown
import com.example.zadumite_frontend.ui.theme.LightGray
import com.example.zadumite_frontend.ui.theme.enterText
import com.example.zadumite_frontend.ui.theme.userCredentials

@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String = "",
    isReadOnly: Boolean = false,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                style = userCredentials
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                style = enterText
            )
        },
        shape = RoundedCornerShape(50.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Brown,
            unfocusedBorderColor = LightBrown,
            cursorColor = LightBrown,
            focusedTextColor = LightBrown,
            focusedContainerColor = LightGray,
            unfocusedContainerColor = DarkGray
        ),
        readOnly = isReadOnly,
        modifier = modifier
            .fillMaxWidth(0.9f)
            .defaultMinSize(minHeight = 55.dp)
    )
}
