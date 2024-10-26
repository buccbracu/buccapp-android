package com.buccbracu.bucc.ui.screens.Login

import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import com.buccbracu.bucc.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.buccbracu.bucc.components.AnimatedVector
import com.buccbracu.bucc.ui.theme.Navy

var darkMode = false;

var bgColor = if(darkMode ==false) Color.White else Navy;
var logoImg = if(darkMode ==false) R.drawable.bucc_logo_light else R.drawable.bucc_logo_dark;

@Composable
fun LandingPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(top = 70.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = logoImg),
                contentDescription = "BUCC Logo",
                modifier = Modifier.size(400.dp)
            )
//            Spacer(modifier = Modifier.height(150.dp))
            Text(
                text = "BRAC University Computer Club",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                lineHeight = 32.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(150.dp))
            AnimatedVector("up_new.json")
            Text(
                text = "Swipe Up to",
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
            Text(
                text = "Upgrade Yourself",
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun BUCCLandingPagepreview(){
    LandingPage()
}