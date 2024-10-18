package com.buccbracu.bucc.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierInfo
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.buccbracu.bucc.R
import com.buccbracu.bucc.ui.theme.palette2DarkPurple3
import com.buccbracu.bucc.ui.theme.palette2Plum
import com.buccbracu.bucc.ui.theme.palette2Skin
import com.buccbracu.bucc.ui.theme.palette3paste
import com.buccbracu.bucc.ui.theme.palette3violet1
import com.buccbracu.bucc.ui.theme.palette3violet2
import com.buccbracu.bucc.ui.theme.palette4lightpink
import com.buccbracu.bucc.ui.theme.palette5LavenderMist
import com.buccbracu.bucc.ui.theme.palette5PeriwinkleBlue
import com.buccbracu.bucc.ui.theme.palette5SpanishPink
import com.buccbracu.bucc.ui.theme.palette6LightIndigo
import com.buccbracu.bucc.ui.theme.palette6LightOrchid
import com.buccbracu.bucc.ui.theme.palette6LightSlateBlue1
import com.buccbracu.bucc.ui.theme.palette6PowderBlue
import com.buccbracu.bucc.ui.theme.paletteDarkPurple
import com.buccbracu.bucc.ui.theme.paletteNavyBlue

@Composable
fun Dashboard(){



    Column(
        modifier = Modifier
            .padding(top = 70.dp)
    ) {
        ProfileSummary()
        LazyColumn(
            modifier = Modifier
                .padding(bottom = 122.dp)
        ) {
            item {
                CardOne()
            }
            item{
                CardTwo()
            }
            item{
                CardThree()
            }
        }

    }

}

@Composable
fun CardOne(){
    val modifierFmw = Modifier.fillMaxWidth()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(start = 15.dp, end = 15.dp),
        colors = CardDefaults.cardColors(palette5SpanishPink)
    ) {

        Column(
            modifier = Modifier
                .padding(top = 5.dp)
        ) {
            Text(
                text = "Dashboard",
                modifier = modifierFmw,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = paletteNavyBlue,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Here you can see pending works",
                modifier = modifierFmw,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                color = palette2Plum
            )
        }
    }
}

@Composable
fun CardTwo(){
    val modifierFmw = Modifier.fillMaxWidth()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 10.dp, start = 15.dp, end = 15.dp),
        colors = CardDefaults.cardColors(palette5LavenderMist)
    ) {

        Column(
            modifier = Modifier
                .padding(top = 5.dp)
        ) {
            Text(
                text = "Dashboard 2",
                modifier = modifierFmw,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif,
                fontSize = 25.sp,
                color = paletteNavyBlue,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Here you can see pending works",
                modifier = modifierFmw,
                fontSize = 20.sp,
                fontFamily = FontFamily.SansSerif,
                textAlign = TextAlign.Center,
                color = palette2Plum
            )
        }
    }
}
@Composable
fun CardThree(){
    val modifierFmw = Modifier.fillMaxWidth()
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(top = 10.dp, start = 15.dp, end = 15.dp),
        colors = CardDefaults.cardColors(palette5PeriwinkleBlue)
    ) {

        Column(
            modifier = Modifier
                .padding(top = 5.dp)
        ) {
            Text(
                text = "Dashboard 3",
                modifier = modifierFmw,
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                color = paletteNavyBlue,
                textAlign = TextAlign.Center
            )
            Text(
                text = "Here you can see pending works",
                modifier = modifierFmw,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = palette2Plum
            )
        }
    }
}

@Composable
fun ProfileSummary(){

    ElevatedCard(
        colors = CardDefaults.cardColors(palette6LightOrchid),
        modifier = Modifier.padding(bottom = 15.dp, start = 15.dp, end = 15.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.nanami),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = "Nafis Sadique Niloy",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = palette3violet2,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = "General Member",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = palette3violet1,
                modifier = Modifier
                    .padding(top = 5.dp)
            )
        }
    }

}