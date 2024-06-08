package com.buccbracu.bucc.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DoubleArrow
import androidx.compose.material.icons.rounded.Group
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.buccbracu.bucc.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Memberslist(){
    Scaffold(
        topBar = {
            Card(modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 5.49.dp)

                ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "All Members")
                    Icon(imageVector = Icons.Rounded.Group, contentDescription = null)
                }
            }
        }
    ){
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            for(i in 1..20){ MembersI()}
        }
    }
}

@Composable
fun MembersI(){
    Card(modifier = Modifier
            .height(80.dp)
            .fillMaxWidth()
            .padding(10.dp)) {
        Row(modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.itachi),
                contentDescription = null ,
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Red,
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
            Text(text = "Itachi Uchiha")
            Icon(imageVector = Icons.Rounded.DoubleArrow ,
                contentDescription = null,
                modifier = Modifier.padding(horizontal = 5.dp))
        }
    }
}

