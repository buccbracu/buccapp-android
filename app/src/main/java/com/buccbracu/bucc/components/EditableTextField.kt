package com.buccbracu.bucc.components

import androidx.compose.material3.MaterialTheme
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

    OutlinedTextField(
        value = text,
        onValueChange = {
            onEdit(it)
        },
        label = {
            Text(label)
        },
        enabled = isEditable,
        shape = MaterialTheme.shapes.medium
    )
}