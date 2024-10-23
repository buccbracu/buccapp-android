package com.buccbracu.bucc.ui

import android.annotation.SuppressLint

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.buccbracu.bucc.backend.local.viewmodels.LoginVM
import com.buccbracu.bucc.ui.screens.LoginScreen


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main(){
    var selectedIndexBotNav by rememberSaveable {
        mutableIntStateOf(0)
    }
    var selectedIndexDrawer by rememberSaveable {
        mutableIntStateOf(-1)
    }
    val navController = rememberNavController()
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var scope = rememberCoroutineScope()
    var scrollState = rememberScrollState()

    // server check

    val loginVM: LoginVM = hiltViewModel()

    LaunchedEffect(Unit) {
        loginVM.loginSuccess(
            memberID = "23341077",
            memberName = "Aadit",
            memberDepartment = "R&D",
            memberDesignation = "AD"
        )
        
    }

    val sessionData by loginVM.allSessions.collectAsState()



    //-------------------------------

    
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { ModalDrawerSheet {
            NavDrawer(
                scrollState = scrollState,
                selectedIndex = selectedIndexDrawer,
                onClick = {item ->
                    navController.navigate(item.title)
                    selectedIndexBotNav = -1
                    scope.launch {
                        drawerState.close()
                    }
                    selectedIndexDrawer = item.index!!
                    
                },
                
            )
            }
        },
        gesturesEnabled = true
    ) {

        Scaffold(
            bottomBar = {
                BottomNavigation(selectedIndex = selectedIndexBotNav) { index ->
                    selectedIndexBotNav = index
                    selectedIndexDrawer = -1
                    println("$selectedIndexDrawer $selectedIndexBotNav")
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
                    AboutUs(sessionData[0])
                }
                composable("About BUCC"){
                    AboutClub()
                }
                composable("LoginScreen") {
                    LoginScreen()
                }
            }
        }
        
    }
}



