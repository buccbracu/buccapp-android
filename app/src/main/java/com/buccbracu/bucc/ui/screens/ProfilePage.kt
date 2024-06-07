package com.buccbracu.bucc.ui.screens

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.buccbracu.bucc.R
import androidx.compose.ui.unit.dp
import com.buccbracu.bucc.ui.theme.BRACblue

@Composable
fun ProfilePage(){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.itachi), contentDescription ="Itachi" )
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)) {
            Image(painter = painterResource(id = R.drawable.itachi),
                contentDescription = "Itachi",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = Color.Red,
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
            Text(text = "Itachi Uchiha",
                color = Color.White,
                )
            Button(onClick = { /*TODO*/ },
                shape = RectangleShape,
                ) {}
        }
        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp))
        {
            ProfileActivity(txt1 = "150", txt2 = "Followers")
            ProfileActivity(txt1 = "20", txt2 = "Following")
            ProfileActivity(txt1 = "39", txt2 = "Posts")
        }
    }
}


@Composable
fun ProfileActivity(txt1:String,txt2:String){
    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Image(painter = painterResource(id = R.drawable.github),
            contentDescription = "github",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop)
        Text(text = txt1, fontWeight = FontWeight.Bold)
        Text(text = "Followers")
    }
}




@Preview
@Composable
fun ProfilePagepreview(){
    ProfilePage()
}