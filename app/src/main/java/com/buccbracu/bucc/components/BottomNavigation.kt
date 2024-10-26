package com.buccbracu.bucc.components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.buccbracu.bucc.components.models.BottomNavItem



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigation(selectedIndex: Int, onItemSelcted: (Int) -> Unit){
    val items = BottomNavItem.bottomNavItemList

    var navSelect by rememberSaveable {
        mutableIntStateOf(0)
    }

    NavigationBar(
        modifier = Modifier.height(120.dp)
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    onItemSelcted(item.idx)
                    navSelect = item.idx
                    item.badge.value = false
                    item.badgeCount.intValue = 0
                          },
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = true,
                icon = {
                    BadgedBox(
                        badge = {
                            if (item.badgeCount.intValue != 0 ){
                                Badge{
                                    Text(text = item.badgeCount.intValue.toString())
                                }
                            }
                            else if (item.badge.value){
                                Badge()
                            }
                        }
                    ) {
                        Icon(imageVector = if (index == navSelect) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.title )
                    }
                }
            )
        }
    }


}

