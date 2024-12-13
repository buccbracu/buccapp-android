package com.buccbracu.bucc

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.buccbracu.bucc.backend.local.preferences.Preferences
import com.buccbracu.bucc.ui.screens.Main
import com.buccbracu.bucc.ui.theme.BuccTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            Preferences.initialize(this, isSystemInDarkTheme())
            val darkMode = runBlocking {
                Preferences.get().darkModeEnabled.first()
            }
            val darkModeEnabled by Preferences.get().darkModeEnabled.collectAsState(darkMode)
            setStatusBarStyle(this, darkModeEnabled)
            BuccTheme(
                darkTheme = darkModeEnabled
            ) {
                Surface(
                ) {
                    Main(darkModeEnabled = darkModeEnabled)
                }
            }
        }
    }
}

private fun setStatusBarStyle(activity: ComponentActivity, isDarkMode: Boolean) {
    val window = activity.window
    WindowCompat.setDecorFitsSystemWindows(window, false)

    val insetsController = WindowInsetsControllerCompat(window, window.decorView)
    insetsController.isAppearanceLightStatusBars = !isDarkMode // true for light content, false for dark
}
