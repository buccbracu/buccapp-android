package com.buccbracu.bucc.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavDrawerItem(
    val icon: ImageVector? = null,
    val title: String,
    val isDivider: Boolean = false,
    val isHeader: Boolean = false
){
    object About: NavDrawerItem(
        icon = Icons.Filled.SupervisedUserCircle,
        title = "About",
    )

    companion object{
        val navDrawerItems = listOf(
            About
        )
    }
}