package com.buccbracu.bucc.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.R

@Composable
fun ProfilePage1(){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp)) {
        TopBar(name = "Profile")
        Spacer(modifier = Modifier.height(14.dp))
        ProfileSection()
    }
}

@Composable
fun TopBar(
    name:String,
    modifier: Modifier=Modifier
){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = modifier
        ){
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = name,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.width(170.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = modifier
        ){
            Icon(imageVector = Icons.Default.Notifications,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Icon(imageVector = Icons.Default.MoreVert,
                contentDescription = "Back",
                tint = Color.Black,
                modifier = modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun ProfileSection(
    modifier: Modifier = Modifier.padding(top = 20.dp)
){
    Column(
        modifier = modifier.fillMaxWidth()
    )
    {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ){
            RoundImage(image = painterResource(id = R.drawable.itachi),
                modifier = Modifier
                    .size(100.dp)
                    .weight(3f))
            Spacer(modifier = Modifier.width(16.dp))
            Details()
        }
        StatSection(modifier = Modifier.weight(7f))
    }
}


@Composable
fun RoundImage(
    image: Painter,
    modifier: Modifier = Modifier
){
    Image(
        painter = image,
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape)
    )
}

@Composable
fun StatSection(modifier: Modifier = Modifier){
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth()
    ) {
        ProfileStats(statNum = "100", statName = "Attendance")
        ProfileStats(statNum = "1", statName = "Github")
        ProfileStats(statNum = "1", statName = "Achivements")
    }
}

@Composable
fun ProfileStats(
    statNum: String,
    statName: String,
    modifier: Modifier =  Modifier
){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Text(
            text = statNum,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier =  Modifier.height(4.dp))
        Text(
            text = statName,
        )
    }
}

@Composable
fun Details(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
    ) {
        Text(text = "NiloyAditya",
            fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true)
@Composable
fun pg1p(){
    ProfilePage1()
}