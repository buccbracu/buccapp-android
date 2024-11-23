package com.buccbracu.bucc.components

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun EditableTextField(
    text: String,
    label: String,
    isEditable: Boolean,
    onEdit: (String) -> Unit
){
    var value by rememberSaveable { mutableStateOf(text) }
    LaunchedEffect(text) {
        value = text
    }
    OutlinedTextField(
        value = value,
        onValueChange = {
            onEdit(it)
        },
        label = {
            Text(label)
        },
        enabled = isEditable
    )
}