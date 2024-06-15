package com.buccbracu.bucc.ui

import android.annotation.SuppressLint
import android.app.ActionBar
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.buccbracu.bucc.components.BottomNavigation
import com.buccbracu.bucc.components.NavDrawer
import com.buccbracu.bucc.components.TopActionBar
import com.buccbracu.bucc.ui.screens.AboutClub
import com.buccbracu.bucc.ui.screens.AboutUs
import com.buccbracu.bucc.ui.screens.Dashboard
import com.buccbracu.bucc.ui.screens.Profile
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main(){
    var selectedIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var scope = rememberCoroutineScope()
    var scrollState = rememberScrollState()
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { ModalDrawerSheet {
            NavDrawer(
                scrollState = scrollState,
                onClick = {title ->
                    navController.navigate(title)
                    selectedIndex = -1
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
            }
        },
        gesturesEnabled = true
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
                // Routes
                composable("Profile"){
                    Profile()
                }
                composable("Dashboard"){
                    Dashboard()
                }
                composable("About Us"){
                    AboutUs()
                }
                composable("About BUCC"){
                    AboutClub()
                }
            }
        }
        
    }
}



