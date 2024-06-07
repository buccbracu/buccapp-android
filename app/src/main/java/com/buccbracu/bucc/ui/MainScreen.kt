package com.buccbracu.bucc.ui

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.buccbracu.bucc.components.BottomNavigation
import com.buccbracu.bucc.ui.screens.Dashboard
import com.buccbracu.bucc.ui.screens.Profile

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main(){
    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val navController = rememberNavController()


    Scaffold(
        bottomBar = {
            BottomNavigation(selectedIndex = selectedIndex) { index ->
                selectedIndex = index
                when (index) {
                    0 -> navController.navigate("Profile")
                    1 -> navController.navigate("Dashboard")
                }

            }
        }
    ){
        NavHost(navController = navController, startDestination = "Profile" ){
            composable("Profile"){
                Profile()
            }
            composable("Dashboard"){
                Dashboard()
            }
        }
    }
}