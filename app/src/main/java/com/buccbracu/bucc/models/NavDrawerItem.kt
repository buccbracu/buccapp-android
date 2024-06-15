package com.buccbracu.bucc.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Apartment
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavDrawerItem(
    val icon: ImageVector? = null,
    val title: String,
    val isDivider: Boolean = false,
    val isHeader: Boolean = false
){
    object AboutUs: NavDrawerItem(
        icon = Icons.Filled.SupervisedUserCircle,
        title = "About Us",
    )
    object AboutClub: NavDrawerItem(
        icon = Icons.Filled.Apartment,
        title = "About BUCC"
    )

    object Divider: NavDrawerItem(
        title = "Divider",
        isDivider = true
    )

    companion object{
        val navDrawerItems = listOf(
            AboutUs,
            AboutClub
        )
    }
}