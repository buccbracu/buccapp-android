package com.buccbracu.bucc.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi

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
import com.buccbracu.bucc.components.NavDrawer
import com.buccbracu.bucc.components.TopActionBar
import kotlinx.coroutines.launch
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.viewmodels.LoginVM
import com.buccbracu.bucc.components.CircularLoadingBasic
import com.buccbracu.bucc.components.models.NavDrawerItem.Companion.navDrawerItems
import com.buccbracu.bucc.components.permissionLauncher
import com.buccbracu.bucc.ui.screens.Login.LandingPage
import kotlinx.coroutines.async
import kotlinx.coroutines.delay


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main(){
    var selectedIndexBotNav by rememberSaveable {
        mutableIntStateOf(0)
    }
    var selectedIndexDrawer by rememberSaveable {
        mutableIntStateOf(-1)
    }
    var sessionSnapshot by remember{
        mutableStateOf(listOf<Session>())
    }
    val navController = rememberNavController()
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var scope = rememberCoroutineScope()
    var scrollState = rememberScrollState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val context = LocalContext.current

    // server check
    val loginVM: LoginVM = hiltViewModel()
    val sessionData by loginVM.allSessions.collectAsState()

    LaunchedEffect(navBackStackEntry?.destination) {
        when (navBackStackEntry?.destination?.route) {
            "Profile" -> selectedIndexDrawer = navDrawerItems.indexOfFirst { it.title == "Profile" }
            "About BUCC" -> selectedIndexDrawer = navDrawerItems.indexOfFirst { it.title == "About BUCC" }
            "About Us" -> selectedIndexDrawer = navDrawerItems.indexOfFirst { it.title == "About Us" }
            "Login" -> selectedIndexDrawer = navDrawerItems.indexOfFirst { it.title == "Login" }
            "SE Dashboard" -> selectedIndexDrawer = navDrawerItems.indexOfFirst { it.title == "SE Dashboard" }
            // Add other routes here if needed
        }
    }



    permissionLauncher(context, Manifest.permission.POST_NOTIFICATIONS)



    //-------------------------------

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                NavDrawer(
                    scrollState = scrollState,
                    selectedIndex = selectedIndexDrawer,
                    onClick = { item ->
                        selectedIndexDrawer = navDrawerItems.indexOf(item)
                        navController.navigate(item.title)
                        selectedIndexBotNav = -1
                        scope.launch {
                            drawerState.close()
                        }
                    },

                    )
            }
        },
        gesturesEnabled = true
    ) {

        Scaffold(
//            bottomBar = {
//                BottomNavigation(selectedIndex = selectedIndexBotNav) { index ->
//                    selectedIndexBotNav = index
//                    selectedIndexDrawer = -1
//                    println("$selectedIndexDrawer $selectedIndexBotNav")
//                    when (index) {
//                        0 -> navController.navigate("Profile")
//                        1 -> navController.navigate("Dashboard")
//                    }
//
//                }
//            },
            topBar = {
                TopActionBar(drawerState = drawerState, scope = scope)
            },

            ) {
            NavHost(navController = navController, startDestination = "About BUCC") {
                // Routes
                composable("Profile") {
                    Profile(
                        sessionData.ifEmpty { sessionSnapshot }
                    )
                }
                composable("SE Dashboard") {
                    SEDashboard()
                }
                composable("About Us") {
                    AboutUs(sessionData[0])
                }
                composable("About BUCC") {
                    AboutClub()
                }
                composable("Login") {
                    LandingPage(navController)
                }

            }
        }

    }
//    if (isSessionReady){
//
//    }
//    else{
//        CircularLoadingBasic("Please wait...")
//    }
}



