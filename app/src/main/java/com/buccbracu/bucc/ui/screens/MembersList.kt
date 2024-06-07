package com.buccbracu.bucc.ui.screens

import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
// import androidx.compose.ui.tooling.preview.Preview
import com.buccbracu.bucc.R
import androidx.compose.ui.unit.dp

@Composable
fun Memberslist(){
    Column {
        MemberlistCard(ProfileImg = "Itachi", ProfileName = "Itachi Uchiha", ActiveTM = "2 hrs ago")
        MemberlistCard(ProfileImg = "Itachi", ProfileName = "Itachi Uchiha", ActiveTM = "2 hrs ago")
        MemberlistCard(ProfileImg = "Itachi", ProfileName = "Itachi Uchiha", ActiveTM = "2 hrs ago")
        MemberlistCard(ProfileImg = "Itachi", ProfileName = "Itachi Uchiha", ActiveTM = "2 hrs ago")
        MemberlistCard(ProfileImg = "Itachi", ProfileName = "Itachi Uchiha", ActiveTM = "2 hrs ago")
    }
}

@Composable
fun MemberlistCard(ProfileImg:String,ProfileName:String,ActiveTM:String){
    Card() {
        Row (horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)){
            Image(painter = painterResource(id = R.drawable.itachi),
                contentDescription = ProfileName,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Red,
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop
                )
            Text(text = ProfileName)
            Text(text = ActiveTM)
        }
    }
}


