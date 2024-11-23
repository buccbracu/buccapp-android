package com.buccbracu.bucc

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.viewmodels.LoginVM
import com.buccbracu.bucc.ui.screens.Main
import com.buccbracu.bucc.ui.theme.BuccTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuccTheme {
                val vm: LoginVM = hiltViewModel()
                Surface(
                ) {
                    Main()
                }
            }
        }
    }
}
