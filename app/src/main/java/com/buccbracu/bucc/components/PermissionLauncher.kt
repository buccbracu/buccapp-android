package com.buccbracu.bucc.components

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat

@Composable
fun permissionLauncher(context: Context, permission: String): Boolean{
    var hasPermission by remember { mutableStateOf(false) }
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        hasPermission = isGranted
    }
    LaunchedEffect(Unit) {
        if (ContextCompat.checkSelfPermission(
                context,
                permission
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Fine location permission granted
            hasPermission = true
        } else {
            // Request location permission
            permissionLauncher.launch(permission)
        }
    }

    return hasPermission
}
