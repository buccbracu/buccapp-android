package com.buccbracu.bucc.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.R
import com.buccbracu.bucc.components.models.NavDrawerItem


@Composable
fun NavDrawer(
    scrollState: ScrollState,
    selectedIndex: Int,
    onClick: (item: NavDrawerItem) -> Unit,
    login: Boolean
){
    val items = if(login) NavDrawerItem.navDrawerItemsLogin else NavDrawerItem.navDrawerItemsGuest
    Column(
        modifier = Modifier
            .verticalScroll(scrollState)
            .width(250.dp)
    ) {

        MiniProfile()

        Text(
            text = "BUCC",
            color = Color(28, 48, 92),
            modifier = Modifier
                .padding(start = 16.dp, end = 20.dp, bottom = 10.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )


        items.forEachIndexed{ index, item ->
            when {
                item.isDivider -> {
                    HorizontalDivider(
                        modifier = Modifier
                            .padding(bottom = 20.dp, top = 20.dp)
                    )
                }

                item.isHeader -> {
                    Text(
                        text = item.title,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 20.dp, top = 20.dp)
                    )
                }

                else -> {
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = selectedIndex == index,
                        onClick = { onClick(item) },
                        icon = {
                            if (selectedIndex == index){
                                Icon(imageVector = item.selectedIcon!!, contentDescription = item.title)
                            }
                            else{
                                Icon(imageVector = item.unselectedIcon!!, contentDescription = item.title)
                            }
                        }
                    )
                }
            }

        }
    }
}

@Composable
fun MiniProfile(){
    Column(
        modifier = Modifier
            .padding(start = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.nanami),
            contentDescription = "Profile",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape),
            contentScale = ContentScale.FillWidth
        )
        Text(
            text = "Nafis Sadique Niloy",
            modifier = Modifier
                .padding(top = 10.dp),
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = "General Member",
            modifier = Modifier
                .padding(top = 5.dp),
            fontWeight = FontWeight.SemiBold,
            fontSize = 12.sp
        )
    }
    HorizontalDivider(
        modifier = Modifier
            .padding(bottom = 20.dp, top = 20.dp)
    )
}