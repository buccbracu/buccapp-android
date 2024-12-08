package com.buccbracu.bucc.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SearchBar(
    query: String,
    onChange: (String) -> Unit,
    label: String,
    placeholder: String
){
    OutlinedTextField(
        value = query,
        onValueChange = onChange,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        label = {
            Text(label)
        },
        placeholder = {
            Text(
                placeholder,
                fontSize = 12.sp,
                fontWeight = FontWeight(300)
            )
        },
        shape = RoundedCornerShape(15.dp)

    )
}