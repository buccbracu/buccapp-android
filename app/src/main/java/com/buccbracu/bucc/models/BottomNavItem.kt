package com.buccbracu.bucc.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    badge: Boolean,
    badgeCount: Int = 0,
    val idx: Int
) {
    var badge = mutableStateOf(badge)
    val badgeCount = mutableIntStateOf(badgeCount)
    object Profile: BottomNavItem(
        title = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        badge = false,
        idx = 0
    )
    object Dashboard: BottomNavItem(
        title = "Dashboard",
        selectedIcon = Icons.Filled.Dashboard,
        unselectedIcon = Icons.Outlined.Dashboard,
        badge = false,
        badgeCount = 5,
        idx = 1
    )
    companion object{
        val bottomNavItemList = listOf(
            Profile,
            Dashboard
        )
    }



}

