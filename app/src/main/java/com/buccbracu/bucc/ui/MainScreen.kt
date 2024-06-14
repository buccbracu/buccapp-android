package com.buccbracu.bucc.ui

import android.annotation.SuppressLint
import android.app.ActionBar
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.buccbracu.bucc.components.BottomNavigation
import com.buccbracu.bucc.components.NavDrawer
import com.buccbracu.bucc.components.TopActionBar
import com.buccbracu.bucc.ui.screens.Dashboard
import com.buccbracu.bucc.ui.screens.Profile

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main(){
    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val navController = rememberNavController()

    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var scope = rememberCoroutineScope()
    var scrollState = rememberScrollState()
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { ModalDrawerSheet {
            NavDrawer(scrollState = scrollState)
            }
        }
    ) {

        Scaffold(
            bottomBar = {
                BottomNavigation(selectedIndex = selectedIndex) { index ->
                    selectedIndex = index
                    when (index) {
                        0 -> navController.navigate("Profile")
                        1 -> navController.navigate("Dashboard")
                    }

                }
            },
            topBar = { TopActionBar(drawerState = drawerState, scope = scope ) }
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
    
}