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
import com.buccbracu.bucc.backend.viewmodels.LoginVM
import com.buccbracu.bucc.components.models.NavDrawerItem.Companion.navDrawerItems
import com.buccbracu.bucc.components.permissionLauncher
import com.buccbracu.bucc.ui.screens.Login.LandingPage
import com.buccbracu.bucc.ui.screens.Login.Logout
import kotlinx.coroutines.delay


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main(darkModeEnabled: Boolean) {
    var selectedIndexBotNav by rememberSaveable {
        mutableIntStateOf(0)
    }
    var selectedIndexDrawer by rememberSaveable {
        mutableIntStateOf(-1)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }
    var logoutComplete by remember {
        mutableStateOf(false)
    }

    var loggingOut by remember {
        mutableStateOf(false)
    }


    val navController = rememberNavController()
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var scope = rememberCoroutineScope()
    var scrollState = rememberScrollState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val context = LocalContext.current

    val loginVM: LoginVM = hiltViewModel()
    val sessionData by loginVM.session.collectAsState()

    LaunchedEffect(sessionData) {
        if(sessionData == null){
            scope.launch {
                loginVM.createEmptySession()
            }
        }
    }

    LaunchedEffect(loggingOut) {
        if(loggingOut){
            scope.launch {
                isLoading = true
                loginVM.logout()
                delay(500)
                isLoading = false
                logoutComplete = true
            }
        }
    }

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
                    login = !(sessionData == null || sessionData!!.name == ""),
                    darkMode = darkModeEnabled
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
            NavHost(navController = navController, startDestination =
            if(sessionData == null || sessionData!!.name == "") "Login Landing"
            else "About BUCC"
            ) {
                // Routes
                composable("Profile") {
                    Profile(sessionData!!)
                }
                composable("SE Dashboard") {
                    SEDashboard()
                }
                composable("About Us") {
                    AboutUs(sessionData!!)
                }
                composable("About BUCC") {
                    AboutClub()
                }
                composable("Login") {
                    LandingPage(loginVM, navController, true)
                }
                composable("Login Landing") {
                    LandingPage(loginVM, navController)
                }
                composable("Department Members"){
                    DeptMemScreen()
                }
                composable("Logout"){
                    Logout(loginVM, navController)
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



