package com.buccbracu.bucc.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.buccbracu.bucc.R

val bucc_desc = "A community for tech enthusiasts from BRAC University, where we explore the latest advancements in computer science and technology."

@Composable
fun AboutUs(){
    Text(text = "We are R&D")
}

@Composable
fun AboutClub(){
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 80.dp)
    ){
        GradientText(text = "BRAC UNIVERSITY COMPUTER CLUB")
        Text(
            text = bucc_desc,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp,start = 16.dp,end = 16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.hero_banner_image),
            contentDescription = "Panel24",
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(20.dp))

        )


    }

}

@Composable
fun GradientText(text: String) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(0xFF3B82F6), // Blue-500
            Color(0xFF2DD4BF)  // Teal-400
        )
    )

    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    // Adjust the text size based on the screen width
    val fontSize = when {
        screenWidthDp >= 900 -> 64f
        screenWidthDp >= 600 -> 48f
        screenWidthDp >= 400 -> 26f
        else -> 24f
    }

        Text(
            text = text,
            style = TextStyle(
                fontSize = fontSize.sp,
                fontWeight = FontWeight.Bold,
                brush = gradient
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
        )

}


