package com.buccbracu.bucc.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.buccbracu.bucc.R

@Composable
fun ProfileView(
    image: String,
    name: String,
    designation: String,
    department: String
){

    AsyncImage(
        model = if (image == "") R.drawable.empty_person
        else image ,
        contentDescription = "Profile image",
        modifier = Modifier
            .size(100.dp)
            .padding(10.dp)
            .clip(CircleShape),
        contentScale = ContentScale.FillWidth
    )
    Text(
        text = name,
        modifier = Modifier
            .padding(top = 10.dp),
        fontWeight = FontWeight.W900,
        fontSize = 18.sp
    )
    Text(
        text = designation,
        modifier = Modifier
            .padding(top = 5.dp),
        fontWeight = FontWeight.W600,
        fontSize = 15.sp
    )
    Text(
        text = department,
        modifier = Modifier
            .padding(top = 0.dp),
        fontWeight = FontWeight.W400,
        fontSize = 12.sp
    )

}