package com.buccbracu.bucc.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.buccbracu.bucc.R
import com.buccbracu.bucc.backend.local.models.Session
import com.buccbracu.bucc.backend.remote.models.Contributor
import com.buccbracu.bucc.backend.remote.models.Member
import com.buccbracu.bucc.backend.viewmodels.ContributorVM
import com.buccbracu.bucc.components.GradientColors
import com.buccbracu.bucc.components.GradientText
import com.buccbracu.bucc.components.NoButtonCircularLoadingDialog
import com.buccbracu.bucc.ui.theme.outfitFontFamily
import kotlinx.coroutines.launch

val bucc_desc = "A community for tech enthusiasts from BRAC University, where we explore the latest advancements in computer science and technology."


@Composable
fun AboutClub(darkMode: Boolean){
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 80.dp)
    ){
        GradientText(
            text = "BRAC UNIVERSITY COMPUTER CLUB",
            fontSize = when {
                screenWidthDp >= 900 -> 46f
                screenWidthDp >= 600 -> 30f
                screenWidthDp >= 400 -> 24f
                else -> 22f
            }.sp,
            colors = listOf(
                Color(19, 124, 193),
                if(darkMode) Color(187,189,191)
                else Color(51, 55, 58),
                Color(19, 124, 193),
                ),
            mode = TileMode.Repeated
        )
        Text(
            text = bucc_desc,
            fontWeight = FontWeight.W500,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 16.dp,start = 16.dp,end = 16.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.hero_banner_image),
            contentDescription = "Panel24",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .clip(RoundedCornerShape(20.dp))


        )


    }

}




