package com.buccbracu.bucc.components.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material.icons.filled.GroupWork
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.EditNote
import androidx.compose.material.icons.outlined.EmojiPeople
import androidx.compose.material.icons.outlined.GroupWork
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.SupervisedUserCircle
import androidx.compose.ui.graphics.vector.ImageVector
import com.buccbracu.bucc.ebgb
import com.buccbracu.bucc.gmex

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
    object DepartmentMembers: NavDrawerItem(
        selectedIcon = Icons.Filled.EmojiPeople,
        unselectedIcon = Icons.Outlined.EmojiPeople,
        title = "Department Members",
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

    object Divider: NavDrawerItem(
        title = "Divider",
        isDivider = true
    )

    companion object{
        fun navDrawerItems(designation: String): List<NavDrawerItem>{
            val list =
                if (ebgb.contains(designation)) listOf(
                    Profile,
                    TaskDashboard,
                    DepartmentMembers,
                    Blogs,
                    Divider,
                    AboutUs,
                    AboutClub,
                    Logout
                )
                else if(designation == "Senior Executive") listOf(
                    Profile,
                    TaskDashboard,
                    DepartmentMembers,
                    Blogs,
                    Divider,
                    AboutUs,
                    AboutClub,
                    Logout
                )
                else if(gmex.contains(designation)) listOf(
                    Profile,
                    Blogs,
                    Divider,
                    AboutUs,
                    AboutClub,
                    Login
                )
                else listOf(
                    Blogs,
                    AboutUs,
                    AboutClub,
                    Login
                )

            return list
        }
    }
}