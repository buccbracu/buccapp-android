package com.buccbracu.bucc.components

import android.widget.Toast
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerModal(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val datePickerState = rememberDatePickerState()
    var isNull by remember{
        mutableStateOf(false)
    }
    val context = LocalContext.current


    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(onClick = {
                isNull = datePickerState.selectedDateMillis == null
                if(!isNull){
                    onDateSelected(datePickerState.selectedDateMillis!!.toDateString())
                    onDismiss()
                }

            }) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
    if(isNull){
        Toast.makeText(context, "Must select a date", Toast.LENGTH_SHORT).show()
        isNull = false
    }
}

fun Long.toDateString(): String {
    val formatter = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
    formatter.timeZone = java.util.TimeZone.getTimeZone("UTC")
    return formatter.format(java.util.Date(this))
}
