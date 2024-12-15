package com.buccbracu.bucc.components.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.filled.GroupWork
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.outlined.AdminPanelSettings
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.EmojiPeople
import androidx.compose.material.icons.outlined.GroupWork
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SupervisedUserCircle
import androidx.compose.ui.graphics.vector.ImageVector
import com.buccbracu.bucc.eb
import com.buccbracu.bucc.gb
import com.buccbracu.bucc.gmex

sealed class NavDrawerItem(
    val selectedIcon: ImageVector? = null,
    val unselectedIcon: ImageVector? = null,
    val title: String,
    val isDivider: Boolean = false,
    val isHeader: Boolean = false,
){
    object Contributors: NavDrawerItem(
        selectedIcon = Icons.Filled.SupervisedUserCircle,
        unselectedIcon = Icons.Outlined.SupervisedUserCircle,
        title = "Contributors",
    )
    object AboutClub: NavDrawerItem(
        selectedIcon = Icons.Filled.Apartment,
        unselectedIcon = Icons.Outlined.Apartment,
        title = "About BUCC",
    )
    object Login: NavDrawerItem(
        selectedIcon = Icons.AutoMirrored.Filled.Login,
        unselectedIcon = Icons.AutoMirrored.Outlined.Login,
        title = "Login",
    )
    object Logout: NavDrawerItem(
        selectedIcon = Icons.AutoMirrored.Filled.Logout,
        unselectedIcon = Icons.AutoMirrored.Outlined.Logout,
        title = "Logout",
    )
    object TaskDashboard: NavDrawerItem(
        selectedIcon = Icons.Filled.GroupWork,
        unselectedIcon = Icons.Outlined.GroupWork,
        title = "Task Dashboard",
    )
    object Members: NavDrawerItem(
        selectedIcon = Icons.Filled.EmojiPeople,
        unselectedIcon = Icons.Outlined.EmojiPeople,
        title = "Members",
    )
    object Blogs: NavDrawerItem(
        selectedIcon = Icons.Filled.EditNote,
        unselectedIcon = Icons.Outlined.EditNote,
        title = "Blogs",
    )
    object Profile: NavDrawerItem(
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        title = "Profile"
    )
    object Dashboard: NavDrawerItem(
        selectedIcon = Icons.Filled.Dashboard,
        unselectedIcon = Icons.Outlined.Dashboard,
        title = "Dashboard"
    )
    object CurrentPanel: NavDrawerItem(
        selectedIcon = Icons.Filled.AdminPanelSettings,
        unselectedIcon = Icons.Outlined.AdminPanelSettings,
        title = "Current Panel"
    )

    object Divider: NavDrawerItem(
        title = "Divider",
        isDivider = true
    )

    companion object{
        fun navDrawerItems(
            designation: String = "",
            department: String = ""
        ): List<NavDrawerItem>{
            val list =
                if (gb.contains(designation) || (department == "Research and Development" && eb.contains(designation))) listOf(
                    Dashboard,
                    TaskDashboard,
                    Members,
                    Blogs,
                    Divider,
                    Contributors,
                    AboutClub,
                    Logout
                )
                else if (eb.contains(designation)) listOf(
                    Dashboard,
                    TaskDashboard,
                    Members,
                    Blogs,
                    Divider,
                    Contributors,
                    AboutClub,
                    Logout
                )
                else if(designation == "Senior Executive") listOf(
                    Dashboard,
                    TaskDashboard,
                    Members,
                    Blogs,
                    Divider,
                    Contributors,
                    AboutClub,
                    Logout
                )
                else if(gmex.contains(designation)) listOf(
                    Dashboard,
                    Blogs,
                    Divider,
                    Contributors,
                    AboutClub,
                    Login
                )
                else listOf(
                    Blogs,
                    Contributors,
                    AboutClub,
                    Login
                )

            return list
        }
    }
}