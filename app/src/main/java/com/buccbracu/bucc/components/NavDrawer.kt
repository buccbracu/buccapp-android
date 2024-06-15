package com.buccbracu.bucc.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.buccbracu.bucc.R
import com.buccbracu.bucc.models.NavDrawerItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun NavDrawer(
    scrollState: ScrollState,
    onClick: (title:String) -> Unit
){
    val items = NavDrawerItem.navDrawerItems
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


        items.forEach{ item ->
            when{
                item.isDivider -> {
                    Divider(
                        modifier = Modifier
                            .padding(bottom = 20.dp, top = 20.dp)
                    )
                }
                item.isHeader ->{
                    Text(text = item.title,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier
                            .padding(start = 20.dp, bottom = 20.dp, top = 20.dp)
                    )
                }
                else -> {
                    DrawerItem(
                        item = item,
                        onClick = {
                            onClick(it)
                        }
                        )
                }
            }
        }
    }
}

@Composable
fun DrawerItem(item: NavDrawerItem, onClick: (title: String) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(50.dp)
            .clickable { onClick(item.title) },
        verticalAlignment = Alignment.CenterVertically

    ){
        Image(
            imageVector = item.icon!!,
            contentDescription = item.title,
            modifier = Modifier.weight(0.5f)
        )
        Text(
            text = item.title,
            modifier = Modifier.weight(2.0f),
        )
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
    Divider(
        modifier = Modifier
            .padding(bottom = 20.dp, top = 20.dp)
    )
}