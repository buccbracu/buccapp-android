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
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.local.models.emptyProfile
import com.buccbracu.bucc.backend.viewmodels.LoginVM
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import com.buccbracu.bucc.components.blog.BlogView
import com.buccbracu.bucc.components.models.NavDrawerItem.Companion.navDrawerItems
import com.buccbracu.bucc.components.permissionLauncher
import com.buccbracu.bucc.notIgnoredRoute

import com.buccbracu.bucc.ui.screens.Dashboard.Dashboard
import com.buccbracu.bucc.ui.screens.Dashboard.EditProfile
import com.buccbracu.bucc.ui.screens.Login.LandingPage
import com.buccbracu.bucc.ui.screens.Login.Logout
import com.buccbracu.bucc.ui.screens.Member.MemberScreen
import com.buccbracu.bucc.ui.screens.Tasks.CreateTask
import com.buccbracu.bucc.ui.screens.Tasks.TaskDashboard


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Main(
    darkModeEnabled: Boolean,
    session: Session?
) {
    var selectedIndexDrawer by rememberSaveable {
        mutableIntStateOf(-1)
    }

    var isLoading by remember {
        mutableStateOf(false)
    }




    val navController = rememberNavController()
    var drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var scope = rememberCoroutineScope()
    var scrollState = rememberScrollState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val context = LocalContext.current

    val loginVM: LoginVM = hiltViewModel()
    val sessionData by loginVM.session.collectAsState(session)
    val profile by loginVM.profile.collectAsState(emptyProfile)

    var navDrawerItemList by remember{
        mutableStateOf(navDrawerItems(profile!!.designation))
    }

    LaunchedEffect(sessionData, profile) {
        if(sessionData == null){
            scope.launch {
                loginVM.createEmptySession()
            }
        }
        if(profile != null){
            if(profile!!.designation != ""){
                navDrawerItemList = navDrawerItems(profile!!.designation, profile!!.buccDepartment)
            }
        }
        else{
            navDrawerItemList = navDrawerItems()
        }
    }

    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    LaunchedEffect(navBackStackEntry?.destination) {
        when (navBackStackEntry?.destination?.route) {
            "Profile" -> selectedIndexDrawer = navDrawerItemList.indexOfFirst { it.title == "Profile" }
            "About BUCC" -> selectedIndexDrawer = navDrawerItemList.indexOfFirst { it.title == "About BUCC" }
            "Contributors" -> selectedIndexDrawer = navDrawerItemList.indexOfFirst { it.title == "Contributors" }
            "Login" -> selectedIndexDrawer = navDrawerItemList.indexOfFirst { it.title == "Login" }
            "Task Dashboard" -> selectedIndexDrawer = navDrawerItemList.indexOfFirst { it.title == "Task Dashboard" }
            // Add other routes here if needed
        }
    }



    permissionLauncher(context, Manifest.permission.POST_NOTIFICATIONS)



    //-------------------------------

    if(sessionData == null){
        NoButtonCircularLoadingDialog("Loading", "Please wait...")
    }
    else{
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                if(currentRoute != "Login Landing" && currentRoute != "Login"){
                    ModalDrawerSheet {
                        NavDrawer(
                            scrollState = scrollState,
                            selectedIndex = selectedIndexDrawer,
                            onClick = { item ->
                                selectedIndexDrawer = navDrawerItemList.indexOf(item)
                                navController.navigate(item.title)
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            darkMode = darkModeEnabled,
                            items = navDrawerItemList
                        )
                    }
                }
            },
            gesturesEnabled = notIgnoredRoute(currentRoute)
        ) {

            Scaffold(
                topBar = {
                    if(notIgnoredRoute(currentRoute)){
                        TopActionBar(
                            drawerState = drawerState,
                            currentPage = currentRoute!!
                        )
                    }

                },

                )
            {
                NavHost(navController = navController, startDestination =
                if(sessionData!!.email == "") "Login Landing"
                else "About BUCC"
                ) {
                    // Routes
                    composable("Dashboard") {
                        Dashboard(navController)
                    }
                    composable("Task Dashboard") {
                        TaskDashboard(navController)
                    }
                    composable("Create Task"){
                        CreateTask(navController)
                    }
                    composable("Contributors") {
                        ContributorScreen()
                    }
                    composable("About BUCC") {
                        AboutClub(darkModeEnabled)
                    }
                    composable("Login") {
                        LandingPage(loginVM, navController, true, darkModeEnabled)
                    }
                    composable("Login Landing") {
                        LandingPage(loginVM, navController, darkMode = darkModeEnabled)
                    }
                    composable("Members"){
                       profile?.let {
                           MemberScreen(
                               department = profile!!.buccDepartment,
                               designation = profile!!.designation
                           )
                       }
                    }
                    composable("Logout"){
                        Logout(loginVM, navController)
                    }
                    composable("Blogs"){
                        ViewBlogs(navController)
                    }
                    composable(
                        "BlogView/{blogId}",
                        arguments = listOf(navArgument("blogId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val blogId = backStackEntry.arguments?.getString("blogId")
                        BlogView(blogId) {
                            navController.popBackStack()
                        }
                    }
                    composable("Edit Profile"){
                        EditProfile(navController)
                    }

                }
            }

            println("CURRENT ROUTE $currentRoute")
        }
    }
//    if (isSessionReady){
//
//    }
//    else{
//        CircularLoadingBasic("Please wait...")
//    }
}



