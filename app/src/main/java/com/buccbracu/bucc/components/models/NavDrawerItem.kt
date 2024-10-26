package com.buccbracu.bucc.components.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.GroupWork
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.GroupWork
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SupervisedUserCircle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavDrawerItem(
    val selectedIcon: ImageVector? = null,
    val unselectedIcon: ImageVector? = null,
    val title: String,
    val isDivider: Boolean = false,
    val isHeader: Boolean = false,
){
    object AboutUs: NavDrawerItem(
        selectedIcon = Icons.Filled.SupervisedUserCircle,
        unselectedIcon = Icons.Outlined.SupervisedUserCircle,
        title = "About Us",
    )
    object AboutClub: NavDrawerItem(
        selectedIcon = Icons.Filled.Apartment,
        unselectedIcon = Icons.Outlined.Apartment,
        title = "About BUCC",
    )
    object LoginScreen: NavDrawerItem(
        selectedIcon = Icons.AutoMirrored.Filled.Login,
        unselectedIcon = Icons.AutoMirrored.Outlined.Login,
        title = "Login",
    )
    object SEDashboard: NavDrawerItem(
        selectedIcon = Icons.Filled.GroupWork,
        unselectedIcon = Icons.Outlined.GroupWork,
        title = "SE Dashboard",
    )
    object Profile: NavDrawerItem(
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        title = "Profile"
    )

    object Divider: NavDrawerItem(
        title = "Divider",
        isDivider = true
    )

    companion object{
        val navDrawerItems = listOf(
            Profile,
            SEDashboard,
            Divider,
            AboutUs,
            AboutClub,
            LoginScreen
        )
    }
}