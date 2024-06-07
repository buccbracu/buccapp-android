package com.buccbracu.bucc.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val badge: Boolean,
    val badgeCount: Int? = null
) {
    object Profile: BottomNavItem(
        title = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        badge = false,
    )
    object Dashboard: BottomNavItem(
        title = "Dashboard",
        selectedIcon = 
    )
}
