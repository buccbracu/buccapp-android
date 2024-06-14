package com.buccbracu.bucc.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.buccbracu.bucc.models.NavDrawerItem

@Composable
fun NavDrawer(scrollState: ScrollState){
    val items = NavDrawerItem.navDrawerItems
    Column {
        items.forEach{ item ->
            when{
                item.isDivider -> {
                    Divider()
                }
                item.isHeader ->{
                    Text(text = item.title)
                }
                else -> {
                    DrawerItem(item)
                }
            }
        }
    }
}

@Composable
fun DrawerItem(item: NavDrawerItem){
    Row{
        Image(
            imageVector = item.icon!!, 
            contentDescription = item.title
        )
        Text(text = item.title)
    }
}