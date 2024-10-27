package com.buccbracu.bucc.ui.screens

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
import androidx.compose.ui.tooling.preview.Preview
import com.buccbracu.bucc.ui.theme.Cyan
import com.buccbracu.bucc.ui.theme.Navy

var darkMode = false;

var bgColor = if(darkMode==false) Color.White else Navy;
var logoImg = if(darkMode==false) R.drawable.bucc_logo_light else R.drawable.bucc_logo_dark;
var textcolor1 = if(darkMode==false) Color.Gray else Cyan;
var textcolor2 = if(darkMode==false) Navy else Color.White;

@Composable
fun BUCCLandingPage() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = logoImg),
                contentDescription = "BUCC Logo",
                modifier = Modifier.size(450.dp)
            )
            Spacer(modifier = Modifier.height(0.2.dp))
            Text(
                text = "BUCC",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(13.dp))
            Text(
                text = "Swipe To",
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                color = textcolor1
            )
            Text(
                text = "Upgrade Yourself",
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                color = textcolor2
            )
        }
    }
}

@Preview
@Composable
fun BUCCLandingPagepreview(){
    BUCCLandingPage()
}